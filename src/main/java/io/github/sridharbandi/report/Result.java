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
package io.github.sridharbandi.report;

import io.github.sridharbandi.modal.Issue;
import org.apache.commons.text.StringEscapeUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Result extends Report {
    private WebDriver driver;

    public Result(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    protected List<Issue> issueList(){
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        return logEntries.getAll().stream()
                .map(LogEntry::getMessage)
                .filter(str -> str.trim().contains("HTMLCS"))
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

    protected List<String> getIssueTechniques(String issueCode) {
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
