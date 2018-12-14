package com.accessibility.handler;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Handler {

    private WebDriver driver;

    public Handler(WebDriver driver) {
        this.driver = driver;
    }

    public void runAccessibility(){
        JavascriptExecutor javascriptExecutor =  (JavascriptExecutor) driver;
    }
}
