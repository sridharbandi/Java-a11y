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
import org.apache.commons.text.StringEscapeUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    public void runAccessibility(String reportName) {
        javascriptExecutor.executeScript(htmlSniffer);
        javascriptExecutor.executeScript(String.format(RUNNER, Accessibility.STANDARD));

        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        List<Issue> issuesList = logEntries.getAll().stream()
                .map(str -> str.getMessage())
                .filter(str -> !str.endsWith("\"done\""))
                .map(str -> str.split("HTMLCS\\]")[1])
                .map(str -> str.substring(0, str.length() - 1))
                .map(issue ->{
                    Issue isue = new Issue();
                    String[] arrIssue = issue.split("\\|");
                    isue.setIssueType(arrIssue[0].trim());
                    isue.setIssueCode(arrIssue[1]);
                    isue.setIssueTechniques(getIssueTechniques(arrIssue[1]));
                    isue.setIssueTag(arrIssue[2]);
                    isue.setIssueId(arrIssue[3]);
                    isue.setIssueMsg(arrIssue[4]);
                    isue.setIssueElement(StringEscapeUtils.unescapeJava(arrIssue[5]));
                    return isue;
                }).collect(Collectors.toList());
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
        issues.setDevice(device());
        issues.setBrowser(browserName());
        issues.setName(reportName.isEmpty() ? pageName() : reportName);
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

    public List<String> getIssueTechniques(String issueCode) {
        Pattern pattern = Pattern.compile("([A-Z]+[0-9]+(,[A-Z]+[0-9]+)*)");
        Matcher matcher = pattern.matcher(issueCode);
        LinkedList<String> codes = new LinkedList<>();
        while (matcher.find()) {
            String str = matcher.group();
            if(str.contains(",")){
                String[] techniques = str.split(",");
                for (String code : techniques){
                    codes.add(code);
                }
            }
            else {
                codes.add(str);
            }
        }
        if(codes.size() != 0){
            codes.remove(0);
        }
        return codes.stream().map(code -> "https://www.w3.org/TR/WCAG20-TECHS/"+code).collect(Collectors.toList());
    }

    private int getCount(List<Issue> issues, IssueType issueType) {
        List<Issue> filteredIssues = issues.stream()
                .filter(issue -> issue.getIssueType().equalsIgnoreCase(issueType.name()))
                .collect(Collectors.toList());
        return filteredIssues.size();
    }

    private String getSize() {
        return getWidth() + " X " + getHeight();
    }

    private long getWidth() {
        return (long) ((JavascriptExecutor) driver).executeScript("return window.innerWidth");
    }

    private long getHeight() {
        return (long) ((JavascriptExecutor) driver).executeScript("return window.innerHeight");
    }

    private String device() {
        int width = (int) getWidth();
        if (width < 768) {
            return "Phone";
        } else if (width < 992) {
            return "Tablet";
        } else if (width < 1200) {
            return "Small Laptop";
        } else {
            return "Laptop/Desktop";
        }
    }

    private String pageName() {
        return driver.getTitle();
    }

    private String browserName() {
        Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
        return upperCaseFirst(capabilities.getBrowserName()) + " " + capabilities.getVersion();
    }

    private String upperCaseFirst(String name) {
        char[] array = name.toCharArray();
        array[0] = Character.toUpperCase(array[0]);
        return new String(array);
    }
}
