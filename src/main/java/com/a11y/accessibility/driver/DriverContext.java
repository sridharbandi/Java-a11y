package com.a11y.accessibility.driver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class DriverContext implements IDriverContext{

    private WebDriver driver;

    public DriverContext(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public String pageTitle() {
        return driver.getTitle();
    }

    @Override
    public long viewPortWidth() {
        return (long) ((JavascriptExecutor) driver).executeScript("return window.innerWidth");
    }

    @Override
    public long viewPortHeight() {
        return  (long) ((JavascriptExecutor) driver).executeScript("return window.innerHeight");
    }

    @Override
    public void executeScript() {

    }
}
