package com.a11y.accessibility.report;

import com.a11y.accessibility.Accessibility;
import com.a11y.accessibility.driver.DriverContext;
import com.a11y.accessibility.ftl.FtlConfig;
import com.a11y.accessibility.modal.Issues;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.Template;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Report extends DriverContext {

    private static Logger LOG = LoggerFactory.getLogger(Report.class);

    public Report(WebDriver driver) {
        super(driver);
    }

    protected Template getTemplate(String template) {
        FtlConfig cfg = FtlConfig.getInstance();
        Template tmplt = null;
        try {
            tmplt = cfg.cfg().getTemplate(template);
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error("Failed to get the freemarker template {}", template);
        }
        return tmplt;
    }

    protected List<Issues> jsonIssues() {
        List<Issues> allissues = null;
        try {
            allissues = Files.walk(Paths.get(Accessibility.REPORT_PATH + "/report/json"))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .filter(file -> FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("json"))
                    .map(file -> {
                        ObjectMapper mapper = new ObjectMapper();
                        Issues issues = null;
                        try {
                            issues = mapper.readValue(file, Issues.class);
                        } catch (IOException e) {
                            e.printStackTrace();
                            LOG.error("Failed to read json file {}", file.getAbsolutePath());
                        }
                        return issues;
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error("Failed to get json files from path {}", Accessibility.REPORT_PATH);
        }
        return allissues;
    }
}
