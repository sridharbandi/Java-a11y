package com.accessibility.report;

public class Issue {
    private String issueType;
    private String issueCode;
    private String issueTag;
    private String issueId;
    private String issueMsg;
    private String issueElement;

    public Issue(String issue) {
        String[] arrIssue = issue.split("\\|");
        this.setIssueType(arrIssue[0]);
        this.setIssueCode(arrIssue[1]);
        this.setIssueTag(arrIssue[2]);
        this.setIssueId(arrIssue[3]);
        this.setIssueMsg(arrIssue[4]);
        this.setIssueElement(arrIssue[5]);
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getIssueCode() {
        return issueCode;
    }

    public void setIssueCode(String issueCode) {
        this.issueCode = issueCode;
    }

    public String getIssueTag() {
        return issueTag;
    }

    public void setIssueTag(String issueTag) {
        this.issueTag = issueTag;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getIssueMsg() {
        return issueMsg;
    }

    public void setIssueMsg(String issueMsg) {
        this.issueMsg = issueMsg;
    }

    public String getIssueElement() {
        return issueElement;
    }

    public void setIssueElement(String issueElement) {
        this.issueElement = issueElement;
    }
}
