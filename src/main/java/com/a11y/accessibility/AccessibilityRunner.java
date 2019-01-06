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

    public AccessibilityRunner(WebDriver driver) {
        super(driver);
        this.driver = driver;
        javascriptExecutor = (JavascriptExecutor) driver;
    }

    @Override
    public int errorCount() {
        return 0;
    }

    @Override
    public int noticeCount() {
        return 0;
    }

    @Override
    public int warningCount() {
        return 0;
    }
}
