package io.github.sridharbandi;

import freemarker.template.TemplateException;
import io.github.sridharbandi.a11y.Engine;
import io.github.sridharbandi.a11y.HTMLCS;
import io.github.sridharbandi.modal.htmlcs.Issue;
import io.github.sridharbandi.modal.htmlcs.Issues;
import io.github.sridharbandi.util.A11y;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Supplier;

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

    public boolean hasErrors(Issues page) {
        return page.getErrors() > 0;
    }

    public boolean hasWarnings(Issues page) {
        return page.getWarnings() > 0;
    }

    public boolean hasNotices(Issues page) {
        return page.getNotices() > 0;
    }

    public boolean pageHasErrors(String pageName) throws IOException {
        return checkFlagged(pageName, 1);
    }

    public boolean pageHasWarnings(String pageName) throws IOException {
        return checkFlagged(pageName, 2);
    }

    public boolean pageHasNotices(String pageName) throws IOException {
        return checkFlagged(pageName, 3);
    }

    private boolean checkFlagged(String pageName, int type) throws IOException {
        List<?> reportList = getPageIssues(pageName);
        if (codesToIgnore != null) {
            reportList = removeIssues(reportList, codesToIgnore);
        }
        //There should only be one page.
        AtomicBoolean flag = new AtomicBoolean();
        int count = 1;
        for (Object page : reportList) {
            //Make sure only evaluating one page. If this fails something is wrong.
            if (count > 1) {
                System.out.println("FAILED REEVALUATE");
                flag.set(false);
                return flag.get();
            }
            switch (type) {
                case 1:
                    flag.set(hasErrors((Issues) page));
                    break;
                case 2:
                    flag.set(hasWarnings((Issues) page));
                    break;
                default:
                     flag.set(hasNotices((Issues) page));
            };
            count = count+1;
        }
        return flag.get();
    }

}
