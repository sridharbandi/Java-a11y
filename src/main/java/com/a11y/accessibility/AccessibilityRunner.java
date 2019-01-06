package com.a11y.accessibility;

import com.a11y.accessibility.issues.IErrors;
import com.a11y.accessibility.issues.INotices;
import com.a11y.accessibility.issues.IWarnings;
import com.a11y.accessibility.issues.Result;
import com.a11y.accessibility.modal.Issue;
import com.a11y.accessibility.modal.Issues;
import com.a11y.accessibility.util.IssueType;
import com.a11y.accessibility.util.SaveJson;
import com.accessibility.util.DateUtil;
import org.openqa.selenium.WebDriver;

import java.util.List;
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

    private int getCount(List<Issue> issues, IssueType issueType) {
        List<Issue> filteredIssues = issues.stream()
                .filter(issue -> issue.getIssueType().equalsIgnoreCase(issueType.name()))
                .collect(Collectors.toList());
        return filteredIssues.size();
    }
}
