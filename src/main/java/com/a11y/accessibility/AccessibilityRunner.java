package com.a11y.accessibility;

import com.a11y.accessibility.htmlcs.HTMLCS;
import com.a11y.accessibility.issues.IErrors;
import com.a11y.accessibility.issues.INotices;
import com.a11y.accessibility.issues.IWarnings;
import com.a11y.accessibility.issues.Result;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class AccessibilityRunner extends Result implements IErrors, IWarnings, INotices {

    private HTMLCS htmlcs = HTMLCS.getInstance();
    private WebDriver driver;
    private JavascriptExecutor javascriptExecutor;
    private int errorCount = 0;
    private int warningCount = 0;
    private int noticeCount = 0;

    public AccessibilityRunner(WebDriver driver) {
        super(driver);
        this.driver = driver;
        javascriptExecutor = (JavascriptExecutor) driver;
    }

    public void execute(){

    }

    public void execute(String pageName){
        executeScript();
    }


    @Override
    public int errorCount() {
        return errorCount;
    }

    @Override
    public int noticeCount() {
        return noticeCount;
    }

    @Override
    public int warningCount() {
        return warningCount;
    }
}
