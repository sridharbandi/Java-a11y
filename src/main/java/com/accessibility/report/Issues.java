package com.accessibility.report;

import java.util.List;

public class Issues {
    private int notices;
    private int warnings;
    private int errors;
    private String standard;
    private String url;
    private List<Issue> issues;

    public int getNotices() {
        return notices;
    }

    public void setNotices(int notices) {
        this.notices = notices;
    }

    public int getWarnings() {
        return warnings;
    }

    public void setWarnings(int warnings) {
        this.warnings = warnings;
    }

    public int getErrors() {
        return errors;
    }

    public void setErrors(int errors) {
        this.errors = errors;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }
}
