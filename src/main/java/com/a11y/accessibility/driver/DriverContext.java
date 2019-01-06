package com.a11y.accessibility.driver;

import com.a11y.accessibility.htmlcs.HTMLCS;
import com.a11y.accessibility.util.Statik;
import com.accessibility.Accessibility;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class DriverContext implements IDriverContext {

    private HTMLCS htmlcs = HTMLCS.getInstance();
    private JavascriptExecutor javascriptExecutor;
    private WebDriver driver;

    public DriverContext(WebDriver driver) {
        this.driver = driver;
        javascriptExecutor = (JavascriptExecutor) driver;
    }

    @Override
    public String pageTitle() {
        return driver.getTitle();
    }

    @Override
    public long viewPortWidth() {
        return (long) javascriptExecutor.executeScript("return window.innerWidth");
    }

    @Override
    public long viewPortHeight() {
        return  (long) javascriptExecutor.executeScript("return window.innerHeight");
    }

    @Override
    public void executeScript() {
        javascriptExecutor.executeScript(htmlcs.getHTMLCS());
        javascriptExecutor.executeScript(String.format(Statik.RUNNER, Accessibility.STANDARD));
    }

    @Override
    public String viewPort() {
        return viewPortWidth()+" X "+viewPortHeight();
    }

}
