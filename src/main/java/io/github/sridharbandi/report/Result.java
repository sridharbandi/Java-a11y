/**
 * Copyright (c) 2019 Sridhar Bandi.
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.github.sridharbandi.report;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.sridharbandi.modal.Issue;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Result extends Report {
    private WebDriver driver;

    public Result(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    protected List<Issue> issueList(List<Map<String, String>> issueList) {
        return issueList.stream().map(entry -> {
            ObjectMapper objectMapper = new ObjectMapper();
            Issue issue = objectMapper.convertValue(entry, Issue.class);
            issue.setIssueTechniques(getIssueTechniques(issue.getIssueCode()));
            return issue;
        }).collect(Collectors.toList());
    }

    protected List<String> getIssueTechniques(String issueCode) {
        Pattern pattern = Pattern.compile("([A-Z]+[0-9]+(,[A-Z]+[0-9]+)*)");
        Matcher matcher = pattern.matcher(issueCode);
        LinkedList<String> codes = new LinkedList<>();
        while (matcher.find()) {
            String match = matcher.group();
            if (match.contains(",")) {
                String[] techniques = match.split(",");
                codes.addAll(Arrays.asList(techniques));
            } else {
                codes.add(match);
            }
        }
        if (codes.size() != 0) {
            codes.remove(0);
        }
        return codes.stream()
                .map(code -> "https://www.w3.org/TR/WCAG20-TECHS/" + code)
                .collect(Collectors.toList());
    }

}
