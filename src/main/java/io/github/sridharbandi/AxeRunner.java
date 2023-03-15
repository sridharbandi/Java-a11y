package io.github.sridharbandi;

import io.github.sridharbandi.a11y.AxeTag;
import io.github.sridharbandi.a11y.Engine;
import io.github.sridharbandi.modal.axe.Issues;
import io.github.sridharbandi.modal.htmlcs.Params;
import io.github.sridharbandi.util.A11y;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class AxeRunner implements IRunner {

    private A11y a11y;
    private Params params;

    public AxeRunner(WebDriver driver) {
        a11y = new A11y(driver);
        params = new Params();
    }

    public AxeRunner setPageTile(String pageTitle) {
        params.setPageTitle(pageTitle);
        return this;
    }

    public AxeRunner setTags(AxeTag... tag) {
        params.setTags(tag);
        return this;
    }

    public AxeRunner disableRules(String... rules) {
        params.disableRules(rules);
        return this;
    }

    public AxeRunner enableRules(String... rules) {
        params.enableRules(rules);
        return this;
    }

    @Override
    public Issues execute() throws IOException {
        return (Issues) a11y.execute(Engine.AXE, params);
    }

    public void generateHtmlReport() throws IOException {
        IRunner.super.generateHtmlReport(a11y, Engine.AXE, Issues.class);
    }
}
