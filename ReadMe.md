# Java-a11y
## Accessibility Automation for Web Apps with Java and Selenium Webdriver.

This project uses [HTML_CodeSniffer](https://squizlabs.github.io/HTML_CodeSniffer/) that checks HTML source code and detects any Accessibility violations. Comes with standards that cover the three (A, AA & AAA) conformance levels of the W3C's Web Content Accessibility Guidelines (WCAG) 2.0 and the U.S. Section 508 legislation.

[![Maven Central](https://img.shields.io/maven-central/v/io.github.sridharbandi/java-a11y.svg)](http://search.maven.org/#search|ga|1|g:"io.github.sridharbandi")
[![jdk badge](https://img.shields.io/badge/jdk-8-green.svg)](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
[![License badge](https://img.shields.io/badge/license-MIT-blue.svg)](https://opensource.org/licenses/MIT)
[![Contributer badge](https://img.shields.io/github/contributors/sridharbandi/Java-a11y.svg)](https://github.com/sridharbandi/Java-a11y/graphs/contributors)

### Features
1. Simple & Easy to use
2. No need of prior knowledge on Accessibility
3. Works with Java Selenium [Webdriver](https://www.selenium.dev/projects/)
4. Rich Reporting
5. Open source

### Usage
For maven based project add the below dependency
```xml
<dependency>
  <groupId>io.github.sridharbandi</groupId>
  <artifactId>java-a11y</artifactId>
  <version>2.1.4</version>
</dependency>
```
For gradle based project add the below dependency
```
compile 'io.github.sridharbandi:java-a11y:2.1.4'
```
For non gradle/maven project download the jar from below mentioned link and add it to CLASSPATH for your project

[https://github.com/sridharbandi/Java-a11y/releases](https://github.com/sridharbandi/Java-a11y/releases)

### Getting Started
Make sure to enable logging capabilities to Webdriver is you are using the version `2.1.2` and below. Below is the example for Chromedriver
```java
ChromeOptions chromeOptions = new ChromeOptions();
LoggingPreferences logPrefs = new LoggingPreferences();
logPrefs.enable(LogType.BROWSER, Level.ALL);
chromeOptions.setCapability("goog:loggingPrefs", logPrefs);
WebDriver driver = new ChromeDriver(chromeOptions);
```

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

This library can be used along with Junit, TestNG and Cucumber/JBehave.

Below is junit example with reporting.

```java
import AccessibilityRunner;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.sridharbandi.util.Standard;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 * A sample test to demonstrate
 */
public class AccessibilityTest {
    private WebDriver driver;
    private static AccessibilityRunner accessibilityRunner;

    @BeforeEach
    public void beforeTest() {
        ChromeDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        chromeOptions.setCapability("goog:loggingPrefs", logPrefs);
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
        accessibilityRunner = new AccessibilityRunner(driver);
        accessibilityRunner.setStandard(Standard.WCAG2AA);
    }

    @org.junit.jupiter.api.Test
    public void googleTest() throws InterruptedException {
        driver.get("https://www.google.co.uk/");
        //executes accessibility on Google Search Page
        accessibilityRunner.execute("Google");
    }

    @org.junit.jupiter.api.Test
    public void w3cschoolsTest() throws InterruptedException {
        driver.get("https://www.w3schools.com/");
        //executes accessibility on W3 Schools home Page
        accessibilityRunner.execute();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @AfterAll
    public static void generateReport() {
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
Accessibility.STANDARD = Standard.Section508;
```

By default it will save reports under project root in `accessibility` folder. However you can configure it where to save
```java
Accessibility.REPORT_PATH =  System.getProperty("user.dir")+"/target/accessibility";
```

### Reports
Below are the report screenshots

#### Consolidated Report
![Index](/readme/index.png)

#### Page Report
![Page](/readme/page.png)

Complete example : [https://github.com/sridharbandi/Java-a11y-example](https://github.com/sridharbandi/Java-a11y-example)

### Todo
1. Remaining Unit tests




