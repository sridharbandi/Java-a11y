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
import java.util.Objects;

public class HtmlCsRunner implements IRunner {

    private A11y a11y;
    private HTMLCS standard;
    private Params params;
    private String[] codes = {};

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


    @Override
    public void execute() throws IOException, URISyntaxException, TemplateException {
        String stdrd = Objects.isNull(standard) ? HTMLCS.WCAG2AA.name() : standard.name();
        params.setStandard(stdrd);
        params.setIgnoreCodes(codes);
        a11y.execute(Engine.HTMLCS, params);
    }

    public void generateHtmlReport() throws IOException {
        IRunner.super.generateHtmlReport(a11y, Engine.HTMLCS, Issues.class);
    }
}
