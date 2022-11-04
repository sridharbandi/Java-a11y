package io.github.sridharbandi;

import freemarker.template.TemplateException;
import io.github.sridharbandi.a11y.Engine;
import io.github.sridharbandi.modal.axe.Issues;
import io.github.sridharbandi.util.A11y;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.URISyntaxException;

public class AxeRunner implements IRunner {

    private A11y a11y;

    public AxeRunner(WebDriver driver) {
        a11y = new A11y(driver);
    }

    @Override
    public void execute() throws IOException, URISyntaxException, TemplateException {
        a11y.execute(Engine.AXE, null);
    }


    public void generateHtmlReport() throws IOException {
        IRunner.super.generateHtmlReport(a11y, Engine.AXE, Issues.class);
    }
}
