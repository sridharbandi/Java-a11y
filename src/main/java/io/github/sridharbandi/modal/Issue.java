package io.github.sridharbandi.modal;

import java.util.List;

public class Issue {
    private String issueType;
    private String issueCode;
    private List<String> issueTechniques;
    private String issueTag;
    private String issueId;
    private String issueMsg;
    private String issueElement;

    public List<String> getIssueTechniques() {
        return issueTechniques;
    }

    public void setIssueTechniques(List<String> issueTechniques) {
        this.issueTechniques = issueTechniques;
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
