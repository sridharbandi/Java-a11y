package io.github.sridharbandi;

import freemarker.template.TemplateException;
import io.github.sridharbandi.a11y.Engine;
import io.github.sridharbandi.a11y.HTMLCS;
import io.github.sridharbandi.modal.htmlcs.Issues;
import io.github.sridharbandi.util.A11y;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

public class HtmlCsRunner implements IRunner {

    private A11y a11y;
    private HTMLCS standard;
    private WebDriver driver;

    public HtmlCsRunner(WebDriver driver) {
        this.driver = driver;
        a11y = new A11y(driver);
    }

    public void setStandard(HTMLCS standard) {
        this.standard = standard;
    }

    @Override
    public void execute() throws IOException, URISyntaxException, TemplateException {
        String stdrd = Objects.isNull(standard) ? HTMLCS.WCAG2AA.name() : standard.name();
        a11y.execute(Engine.HTMLCS, stdrd);
    }


    public void generateHtmlReport() throws IOException {
        IRunner.super.generateHtmlReport(a11y, Engine.HTMLCS, Issues.class);
    }
}
