package com.accessibility.handler;

import com.accessibility.htmlcs.HtmlCodeSniffer;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Handler {

    private WebDriver driver;
    private JavascriptExecutor javascriptExecutor;
    private String htmlSniffer;

    public Handler(WebDriver driver) {
        this.driver = driver;
        javascriptExecutor =  (JavascriptExecutor) driver;
        htmlSniffer = HtmlCodeSniffer.getJS();
    }

    public void runAccessibility(String reportName){
        javascriptExecutor.executeScript(htmlSniffer);

    }
}
