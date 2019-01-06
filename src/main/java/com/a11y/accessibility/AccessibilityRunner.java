package com.a11y.accessibility;

import com.a11y.accessibility.htmlcs.HTMLCS;
import com.a11y.accessibility.issues.IErrors;
import com.a11y.accessibility.issues.INotices;
import com.a11y.accessibility.issues.IWarnings;
import com.a11y.accessibility.issues.Result;
import com.a11y.accessibility.modal.Issue;
import com.a11y.accessibility.util.IssueType;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

public class AccessibilityRunner extends Result implements IErrors, IWarnings, INotices {

    private HTMLCS htmlcs = HTMLCS.getInstance();
    private WebDriver driver;
    private JavascriptExecutor javascriptExecutor;
    private int errorCount = 0;
    private int warningCount = 0;
    private int noticeCount = 0;
    private List<Issue> issueList;

    public AccessibilityRunner(WebDriver driver) {
        super(driver);
        this.driver = driver;
        javascriptExecutor = (JavascriptExecutor) driver;
    }

    public void execute(){
        execute(pageTitle());
    }

    public void execute(String pageName){
        executeScript();
        issueList = issueList();

    }


    public List<Issue> getIssueList(){
        return issueList;
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
