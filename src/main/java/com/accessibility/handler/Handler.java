package com.accessibility.handler;

import com.accessibility.Accessibility;
import com.accessibility.htmlcs.HtmlCodeSniffer;
import com.accessibility.report.Issue;
import com.accessibility.report.Issues;
import com.accessibility.util.IssueType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Handler {

    private WebDriver driver;
    private JavascriptExecutor javascriptExecutor;
    private String htmlSniffer;
    private String runner = "window.HTMLCS_RUNNER.run('%s');";

    public Handler(WebDriver driver) {
        this.driver = driver;
        javascriptExecutor =  (JavascriptExecutor) driver;
        htmlSniffer = HtmlCodeSniffer.getJS();
    }

    public void runAccessibility(String reportName){
        javascriptExecutor.executeScript(htmlSniffer);
        javascriptExecutor.executeScript(String.format(runner,Accessibility.STANDARD));

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

    }

    private int getCount(List<Issue> issues, IssueType issueType){
        List<Issue> filteredIssues = issues.stream()
                .filter(issue -> issue.getIssueType().equalsIgnoreCase(issueType.name()))
                .collect(Collectors.toList());
        return filteredIssues.size();
    }
}
