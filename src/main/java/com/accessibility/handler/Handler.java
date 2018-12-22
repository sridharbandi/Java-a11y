package com.accessibility.handler;

import com.accessibility.Accessibility;
import com.accessibility.htmlcs.HtmlCodeSniffer;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Handler {

    private WebDriver driver;
    private JavascriptExecutor javascriptExecutor;
    private String htmlSniffer;
    private String runner = "window.HTMLCS_RUNNER.run('%s');";

    public Handler(WebDriver driver) {
        this.driver = driver;
        javascriptExecutor =  (JavascriptExecutor) driver;
        htmlSniffer = HtmlCodeSniffer.getJS();
    }

    public void runAccessibility(String reportName){
        javascriptExecutor.executeScript(htmlSniffer);
        javascriptExecutor.executeScript(String.format(runner,Accessibility.STANDARD));
        //Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
       // LoggingPreferences logPrefs  = (LoggingPreferences) capabilities.getCapability(CapabilityType.LOGGING_PREFS);
        // logPrefs.getEnabledLogTypes();

        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        List<String> issues = logEntries.getAll().stream().map(str -> str.getMessage().endsWith("\"done\"")?"":str.getMessage().substring(str.getMessage().indexOf("[HTMLCS]"))).collect(Collectors.toList());
        for (String entry : issues) {
            System.out.println( entry);
        }
    }
}
