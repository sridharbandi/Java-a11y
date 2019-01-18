package com.a11y.accessibility.driver;

import com.a11y.accessibility.Accessibility;
import com.a11y.accessibility.htmlcs.HTMLCS;
import com.a11y.accessibility.util.Statik;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

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
        return (long) javascriptExecutor.executeScript("return window.innerHeight");
    }

    @Override
    public void executeScript() {
        javascriptExecutor.executeScript(htmlcs.getHTMLCS());
        javascriptExecutor.executeScript(String.format(Statik.RUNNER, Accessibility.STANDARD));
    }

    @Override
    public String viewPort() {
        return viewPortWidth() + " X " + viewPortHeight();
    }

    @Override
    public String url() {
        return driver.getCurrentUrl();
    }

    @Override
    public String device() {
        int width = (int) viewPortWidth();
        if (width < 768) {
            return "Phone";
        } else if (width < 992) {
            return "Tablet";
        } else if (width < 1200) {
            return "Small Laptop";
        } else {
            return "Laptop/Desktop";
        }
    }

    @Override
    public String browserName() {
        Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
        return upperCaseFirst(capabilities.getBrowserName()) + " " + capabilities.getVersion();
    }

    private String upperCaseFirst(String name) {
        char[] array = name.toCharArray();
        array[0] = Character.toUpperCase(array[0]);
        return new String(array);
    }

}
