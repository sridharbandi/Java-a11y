package com.a11y.accessibility.issues;

import com.accessibility.report.Issue;
import org.apache.commons.text.StringEscapeUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Result {
    private WebDriver driver;

    public Result(WebDriver driver) {
        this.driver = driver;
    }


    public List<Issue> issueList(){
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        return logEntries.getAll().stream()
                .map(str -> str.getMessage())
                .filter(str -> !str.endsWith("\"done\""))
                .map(str -> str.split("HTMLCS\\]")[1])
                .map(str -> str.substring(0, str.length() - 1))
                .map(issue ->{
                    Issue _issue = new Issue();
                    String[] arrIssue = issue.split("\\|");
                    _issue.setIssueType(arrIssue[0].trim());
                    _issue.setIssueCode(arrIssue[1]);
                    _issue.setIssueTechniques(getIssueTechniques(arrIssue[1]));
                    _issue.setIssueTag(arrIssue[2]);
                    _issue.setIssueId(arrIssue[3]);
                    _issue.setIssueMsg(arrIssue[4]);
                    _issue.setIssueElement(StringEscapeUtils.unescapeJava(arrIssue[5]));
                    return _issue;
                }).collect(Collectors.toList());
    }

    public List<String> getIssueTechniques(String issueCode) {
        Pattern pattern = Pattern.compile("([A-Z]+[0-9]+(,[A-Z]+[0-9]+)*)");
        Matcher matcher = pattern.matcher(issueCode);
        LinkedList<String> codes = new LinkedList<>();
        while (matcher.find()) {
            String match = matcher.group();
            if(match.contains(",")){
                String[] techniques = match.split(",");
                for (String code : techniques){
                    codes.add(code);
                }
            }
            else {
                codes.add(match);
            }
        }
        if(codes.size() != 0){
            codes.remove(0);
        }
        return codes.stream()
                .map(code -> "https://www.w3.org/TR/WCAG20-TECHS/"+code)
                .collect(Collectors.toList());
    }

}
