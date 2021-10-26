package io.github.sridharbandi;

import freemarker.template.TemplateException;
import io.github.sridharbandi.a11y.Engine;
import io.github.sridharbandi.a11y.HTMLCS;
import io.github.sridharbandi.modal.htmlcs.Issues;
import io.github.sridharbandi.util.A11y;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class HtmlCsRunner implements IRunner {

    private A11y a11y;
    private HTMLCS standard;
    private WebDriver driver;
    private String[] codesToIgnore;

    public HtmlCsRunner(WebDriver driver) {
        this.driver = driver;
        a11y = new A11y(driver);
    }

    public void setCodesToIgnore(String[] ignoreCodes) {
        codesToIgnore = ignoreCodes;
    }

    public String[] getCodesToIgnore() {
        return codesToIgnore;
    }

    public void setStandard(HTMLCS standard) {
        this.standard = standard;
    }

    @Override
    public void execute() throws IOException, URISyntaxException, TemplateException {
        String stdrd = Objects.isNull(standard) ? HTMLCS.WCAG2AA.name() : standard.name();
        a11y.execute(Engine.HTMLCS, stdrd);
    }

    public void execute(String pageName) throws IOException, URISyntaxException, TemplateException {
        String stdrd = Objects.isNull(standard) ? HTMLCS.WCAG2AA.name() : standard.name();
        a11y.execute(Engine.HTMLCS, stdrd, pageName);
    }

    public void generateHtmlReport() throws IOException {
        IRunner.super.generateHtmlReport(a11y, Engine.HTMLCS, Issues.class, codesToIgnore);
    }

    public List<?> getIssues() throws IOException {
        return a11y.jsonReports(Engine.HTMLCS, Issues.class);
    }

    public List<?> getPageIssues(String pageName) throws IOException {
        return a11y.jsonPageReport(Engine.HTMLCS, Issues.class, pageName);
    }

    public boolean hasErrors() throws IOException {
        List<?> issues = getIssues();
        AtomicBoolean flag = new AtomicBoolean(false);
        for (Object issue : issues) {
            if (((Issues) issues).getErrors() > 0) {
                flag.set(true);
                break;
            }
        }
        return flag.get();
    }

    public boolean hasWarnings() throws IOException {
        List<?> issues = getIssues();
        AtomicBoolean flag = new AtomicBoolean(false);
        for (Object issue : issues) {
            if (((Issues) issues).getWarnings() > 0) {
                flag.set(true);
                break;
            }
        }
        return flag.get();
    }

    public boolean hasNotices() throws IOException {
        List<?> issues = getIssues();
        AtomicBoolean flag = new AtomicBoolean(false);
        for (Object issue : issues) {
            if (((Issues) issues).getNotices() > 0) {
                flag.set(true);
                break;
            }
        }
        return flag.get();
    }

    public boolean pageHasErrors(String pageName) throws IOException {
        List<Issues> issues = (List<Issues>) getPageIssues(pageName);
        AtomicBoolean flag = new AtomicBoolean(false);
        for (Issues issue : issues) {
            if (issue.getErrors() > 0) {
                flag.set(true);
                break;
            }
        }
        return flag.get();
    }

    public boolean pageHasWarnings(String pageName) throws IOException {
        List<Issues> issues = (List<Issues>) getPageIssues(pageName);
        AtomicBoolean flag = new AtomicBoolean(false);
        for (Issues issue : issues) {
            if (issue.getWarnings() > 0) {
                flag.set(true);
                break;
            }
        }
        return flag.get();
    }

    public boolean pageHasNotices(String pageName) throws IOException {
        List<Issues> issues = (List<Issues>) getPageIssues(pageName);
        AtomicBoolean flag = new AtomicBoolean(false);
        for (Issues issue : issues) {
            if (issue.getNotices() > 0) {
                flag.set(true);
                break;
            }
        }
        return flag.get();
    }

}
