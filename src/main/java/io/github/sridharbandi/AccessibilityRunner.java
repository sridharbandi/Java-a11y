/**
 * Copyright (c) 2019 Sridhar Bandi.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.github.sridharbandi;

import io.github.sridharbandi.issues.IErrors;
import io.github.sridharbandi.issues.INotices;
import io.github.sridharbandi.issues.IWarnings;
import io.github.sridharbandi.modal.Issue;
import io.github.sridharbandi.modal.Issues;
import io.github.sridharbandi.report.Result;
import io.github.sridharbandi.util.IssueType;
import io.github.sridharbandi.util.SaveJson;
import io.github.sridharbandi.util.DateUtil;
import io.github.sridharbandi.ftl.FtlConfig;
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
        Template tmplPage = FtlConfig.getInstance().getTemplate("page.ftl");
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
            List<Issue> errors = issues.getIssues().stream().filter(issue -> issue.getIssueType().equalsIgnoreCase(IssueType.Error.name())).collect(Collectors.toList());
            map.put("errors", errors);
            List<Issue> warnings = issues.getIssues().stream().filter(issue -> issue.getIssueType().equalsIgnoreCase(IssueType.Warning.name())).collect(Collectors.toList());
            map.put("warnings", warnings);
            List<Issue> notices = issues.getIssues().stream().filter(issue -> issue.getIssueType().equalsIgnoreCase(IssueType.Notice.name())).collect(Collectors.toList());
            map.put("notices", notices);
            save(tmplPage, map,issues.getReportID());
        }

        Template tmplIndex = FtlConfig.getInstance().getTemplate("index.ftl");
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
