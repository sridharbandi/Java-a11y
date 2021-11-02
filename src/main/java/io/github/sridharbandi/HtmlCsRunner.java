package io.github.sridharbandi;

import freemarker.template.TemplateException;
import io.github.sridharbandi.a11y.Engine;
import io.github.sridharbandi.a11y.HTMLCS;
import io.github.sridharbandi.modal.htmlcs.Issues;
import io.github.sridharbandi.modal.htmlcs.Params;
import io.github.sridharbandi.util.A11y;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class HtmlCsRunner implements IRunner {

    private A11y a11y;
    private HTMLCS standard;
    private Params params;
    private String[] codes = {};
    private String pageTitle = "";

    public HtmlCsRunner(WebDriver driver) {
        a11y = new A11y(driver);
        params = new Params();
    }

    public void setStandard(HTMLCS standard) {
        this.standard = standard;
    }

    public void setIgnoreCodes(String[] codes){
        this.codes = codes;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }


    @Override
    public void execute() throws IOException, URISyntaxException, TemplateException {
        String stdrd = Objects.isNull(standard) ? HTMLCS.WCAG2AA.name() : standard.name();
        params.setStandard(stdrd);
        params.setIgnoreCodes(codes);
        if (pageTitle.length() > 0) {
            params.setPageTitle(pageTitle);
        }
        a11y.execute(Engine.HTMLCS, params);
        //Reset title after executed for report
        params.setPageTitle(null);
        setPageTitle("");
    }

    public void generateHtmlReport() throws IOException {
        IRunner.super.generateHtmlReport(a11y, Engine.HTMLCS, Issues.class);
    }

    public List<?> getIssues() throws IOException {
        return a11y.jsonReports(Engine.HTMLCS, Issues.class);
    }

    public List<?> getPageIssues(String pageName) throws IOException {
        return a11y.jsonPageReport(Engine.HTMLCS, Issues.class, pageName);
    }

    public boolean pageHasErrors(Issues page) {
        return page.getErrors() > 0;
    }

    public boolean pageHasWarnings(Issues page) {
        return page.getWarnings() > 0;
    }

    public boolean pageHasNotices(Issues page) {
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
        //There should only be one page.
        AtomicBoolean flag = new AtomicBoolean();
        int count = 1;
        for (Object page : reportList) {
            //Make sure only evaluating one page. If this fails something is wrong.
            if (count > 1) {
                flag.set(false);
                return flag.get();
            }
            switch (type) {
                case 1:
                    flag.set(pageHasErrors((Issues) page));
                    break;
                case 2:
                    flag.set(pageHasWarnings((Issues) page));
                    break;
                default:
                    flag.set(pageHasNotices((Issues) page));
            };
            count = count+1;
        }
        return flag.get();
    }

}
