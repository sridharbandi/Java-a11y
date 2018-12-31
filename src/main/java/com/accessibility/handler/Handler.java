package com.accessibility.handler;

import com.accessibility.Accessibility;
import com.accessibility.htmlcs.HtmlCodeSniffer;
import com.accessibility.report.Issue;
import com.accessibility.report.Issues;
import com.accessibility.report.SaveReport;
import com.accessibility.util.DateUtil;
import com.accessibility.util.IssueType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;

import java.util.List;
import java.util.stream.Collectors;

public class Handler {

    private WebDriver driver;
    private JavascriptExecutor javascriptExecutor;
    private String htmlSniffer;
    private static String RUNNER = "window.HTMLCS_RUNNER.run('%s');";

    public Handler(WebDriver driver) {
        this.driver = driver;
        javascriptExecutor = (JavascriptExecutor) driver;
        htmlSniffer = HtmlCodeSniffer.getJS();
    }

    //TODO - add device width and height
    public void runAccessibility(String reportName) {
        javascriptExecutor.executeScript(htmlSniffer);
        javascriptExecutor.executeScript(String.format(RUNNER, Accessibility.STANDARD));

        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        List<Issue> issuesList = logEntries.getAll().stream()
                .map(str -> str.getMessage())
                .filter(str -> !str.endsWith("\"done\""))
                .map(str -> str.split("HTMLCS\\]")[1])
                .map(str -> str.substring(0, str.length() - 1))
                .map(issue -> new Issue(issue)).collect(Collectors.toList());
        int notices = getCount(issuesList, IssueType.Notice);
        int warnings = getCount(issuesList, IssueType.Warning);
        int errors = getCount(issuesList, IssueType.Error);
        Issues issues = new Issues();
        issues.setNotices(notices);
        issues.setWarnings(warnings);
        issues.setErrors(errors);
        issues.setStandard(Accessibility.STANDARD);
        issues.setUrl(driver.getCurrentUrl());
        issues.setDate(DateUtil.getDate());
        issues.setSize(getSize());
        issues.setIssues(issuesList);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = null;
        try {
            jsonInString = mapper.writeValueAsString(issues);
            jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(issues);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(jsonInString);
        SaveReport.save(jsonInString, reportName);

    }

    private int getCount(List<Issue> issues, IssueType issueType) {
        List<Issue> filteredIssues = issues.stream()
                .filter(issue -> issue.getIssueType().equalsIgnoreCase(issueType.name()))
                .collect(Collectors.toList());
        return filteredIssues.size();
    }

    private String getSize() {
        long width = (long) ((JavascriptExecutor) driver).executeScript("return window.innerWidth");
        long height = (long) ((JavascriptExecutor) driver).executeScript("return window.innerHeight");
        return width + " X " + height;
    }
}
