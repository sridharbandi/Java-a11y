# Java-a11y
## Accessibility Automation for Web Apps with Java and Selenium Webdriver.

This project uses [HTML_CodeSniffer](https://squizlabs.github.io/HTML_CodeSniffer/) that checks HTML source code and detects any Accessibility violations. Comes with standards that cover the three (A, AA & AAA) conformance levels of the W3C's Web Content Accessibility Guidelines (WCAG) 2.0 and the U.S. Section 508 legislation.

### Features
1. Simple & Easy to use
2. No need of prior knowledge on Accessibility
3. Works with Java Selenium [Webdriver](https://www.seleniumhq.org/projects/webdriver/)
4. Rich Reporting
5. Open source

### Download
Download the latest release from [https://github.com/sridharbandi/Java-a11y/releases](https://github.com/sridharbandi/Java-a11y/releases)

### Usage
Download the jar from above mentioned link and add it to CLASSPATH for you Selenium Java project

### Getting Started
This library is very easy to use. Create object of `AccessibilityRunner` as below
```java
AccessibilityRunner accessibilityRunner = new AccessibilityRunner(driver);
```

Once after you navigated to any page/popup with Selenium Webdriver execute Accessibility on that particular page/popup
```java
accessibilityRunner.execute();
//or you can pass report name
accessibilityRunner.execute("Google");
```

The above `execute` will also generate `JSON Report` on accessibility issues at page/popup level

Once after all the tests executed, you can call the below method to generate consolidated `HTML Report` on accessibility issues
```java
accessibilityRunner.generateHtmlReport();
```

This library can be used along with Junit, TestNG and Cucumber/JBehave
Below is junit example with reporting

```java
import com.a11y.accessibility.AccessibilityRunner;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 * A sample test to demonstrate
 */
public class Test {
    private WebDriver driver;
    private static AccessibilityRunner accessibilityRunner;

    @Before
    public void beforeTest() {
        ChromeDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        chromeOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
        //Accessibility Runner Object
        accessibilityRunner = new AccessibilityRunner(driver);
    }

    @org.junit.Test
    public void googleTest() throws InterruptedException {
        driver.get("https://www.google.co.uk/");
        //executes accessibility on Google Search Page
        accessibilityRunner.execute("Google");
    }
    @org.junit.Test
    public void w3cschoolsTest() throws InterruptedException {
        driver.get("https://www.w3schools.com/");
        //executes accessibility on W3Schools Page
        accessibilityRunner.execute();
    }

    @After
    public void tearDown() {
         driver.quit();
    }

    @AfterClass
    public static void generateReport(){
         //Generated Consolidated HTML Report
         accessibilityRunner.generateHtmlReport();
    }
}

```

By default it will check against `WCAG2AA` standards. However you can configure it to standard you want to test with
```java
Accessibility.STANDARD = Standard.WCAG2AAA;
//Or
Accessibility.STANDARD = Standard.WCAG2AA;
//Or
Accessibility.STANDARD = Standard.WCAG2A;
//Or
Accessibility.STANDARD = Standard.SECTION508;
```








