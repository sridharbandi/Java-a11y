package com.a11y.accessibility;

import com.a11y.accessibility.issues.IErrors;
import com.a11y.accessibility.issues.INotices;
import com.a11y.accessibility.issues.IWarnings;
import com.a11y.accessibility.modal.Issue;
import com.a11y.accessibility.modal.Issues;
import com.a11y.accessibility.report.Result;
import com.a11y.accessibility.util.IssueType;
import com.a11y.accessibility.util.SaveJson;
import com.accessibility.util.DateUtil;
import freemarker.template.Template;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class AccessibilityRunner extends Result implements IErrors, IWarnings, INotices {

    private List<Issue> issueList;
    private Issues issues;

    public AccessibilityRunner(WebDriver driver) {
        super(driver);
    }

    public void execute(){
        execute(pageTitle());
    }

    public void execute(String pageName){
        executeScript();
        issueList = issueList();
        issues = getIssues(pageName);
        SaveJson.save(issues, pageName);
    }

    public List<Issue> getIssueList(){
        return issueList;
    }

    public Issues getIssues(){
        return issues;
    }

    private Issues getIssues(String reportName){
        Issues issues = new Issues();
        issues.setNotices(noticeCount());
        issues.setWarnings(warningCount());
        issues.setErrors(errorCount());
        issues.setStandard(Accessibility.STANDARD);
        issues.setUrl(url());
        issues.setDate(DateUtil.getDate());
        issues.setSize(viewPort());
        issues.setDevice(device());
        issues.setBrowser(browserName());
        issues.setName(reportName.isEmpty() ? pageTitle() : reportName);
        issues.setReportID(UUID.randomUUID().toString().replace("-", ""));
        issues.setIssues(issueList);
        return issues;
    }

    @Override
    public int errorCount() {
        return getCount(issueList, IssueType.Error);
    }

    @Override
    public int noticeCount() {
        return getCount(issueList, IssueType.Notice);
    }

    @Override
    public int warningCount() {
        return getCount(issueList, IssueType.Warning);
    }

    public void generateHtmlReport(){
        Template tmplPage = getTemplate("page.ftl");
        List<Issues> allissues = jsonIssues();

        for(Issues issues : allissues) {
            Map<String, Object> map = new HashMap<>();
            map.put("reportname", issues.getName());
            map.put("url", issues.getUrl());
            map.put("standard", issues.getStandard());
            map.put("browser", issues.getBrowser());
            map.put("browsersize", issues.getSize());
            map.put("device", issues.getDevice());
            map.put("datetime", issues.getDate());
            map.put("errorcount", issues.getErrors());
            map.put("warningcount", issues.getWarnings());
            map.put("noticecount", issues.getNotices());
            List<Issue> errors = issues.getIssues().stream().filter(issue -> issue.getIssueType().equalsIgnoreCase(com.accessibility.util.IssueType.Error.name())).collect(Collectors.toList());
            map.put("errors", errors);
            List<Issue> warnings = issues.getIssues().stream().filter(issue -> issue.getIssueType().equalsIgnoreCase(com.accessibility.util.IssueType.Warning.name())).collect(Collectors.toList());
            map.put("warnings", warnings);
            List<Issue> notices = issues.getIssues().stream().filter(issue -> issue.getIssueType().equalsIgnoreCase(com.accessibility.util.IssueType.Notice.name())).collect(Collectors.toList());
            map.put("notices", notices);
            save(tmplPage, map,issues.getReportID());
        }

        Template tmplIndex = getTemplate("index.ftl");
        Map<String, Object> map = new HashMap<>();
        map.put("reportname", "Accessibility Report");
        map.put("urlcount", reportUrls(allissues).size());
        map.put("errorscount", count(reportErrors(allissues)));
        map.put("warningscount", count(reportWarnings(allissues)));
        map.put("noticescount", count(reportNotices(allissues)));
        map.put("urls",  reportUrls(allissues));
        map.put("errors", reportErrors(allissues));
        map.put("warnings", reportWarnings(allissues));
        map.put("notices", reportNotices(allissues));
        map.put("issues", allissues);
        save(tmplIndex, map, "index");
    }


    private int getCount(List<Issue> issues, IssueType issueType) {
        List<Issue> filteredIssues = issues.stream()
                .filter(issue -> issue.getIssueType().equalsIgnoreCase(issueType.name()))
                .collect(Collectors.toList());
        return filteredIssues.size();
    }
}
