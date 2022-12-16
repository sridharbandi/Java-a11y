# Java-a11y
## Accessibility Automation for Web Apps with Java and Selenium Webdriver.

>Note If you are using version 2.1.4 and below, refer [readme](/ReadMe_Pre.md)

### This project uses [HTML CodeSniffer](https://squizlabs.github.io/HTML_CodeSniffer/) and [Deque Axe](https://www.deque.com/)

**HTML CodeSniffer** : checks HTML source code and detects any Accessibility violations. Comes with standards that cover the three (A, AA & AAA) conformance levels of the W3C's Web Content Accessibility Guidelines (WCAG) 2.1 and the U.S. Section 508 legislation.

**Deque Axe** : World’s leading digital accessibility toolkit. Powerful and accurate accessibility toolkit can get you to 80% issue coverage, or more, during development.

[![Maven Central](https://img.shields.io/maven-central/v/io.github.sridharbandi/java-a11y.svg)](http://search.maven.org/#search|ga|1|g:"io.github.sridharbandi")
[![jdk badge](https://img.shields.io/badge/jdk-8-green.svg)](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/8b32dbe54c8d4b5e902390926e8b0d46)](https://www.codacy.com/gh/sridharbandi/Java-a11y/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=sridharbandi/Java-a11y&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/8b32dbe54c8d4b5e902390926e8b0d46)](https://www.codacy.com/gh/sridharbandi/Java-a11y/dashboard?utm_source=github.com&utm_medium=referral&utm_content=sridharbandi/Java-a11y&utm_campaign=Badge_Coverage)
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
  <version>3.0.2</version>
</dependency>
```
For gradle based project add the below dependency
```
compile 'io.github.sridharbandi:java-a11y:3.0.2'
```
For non gradle/maven project download the jar from below mentioned link and add it to CLASSPATH for your project

[https://github.com/sridharbandi/Java-a11y/releases](https://github.com/sridharbandi/Java-a11y/releases)

### Getting Started
#### Using HTML CodeSniffer
Create object of `HtmlCsRunner` as below. `driver` will be your WebDriver instance.
```java
HtmlCsRunner htmlCsRunner = new HtmlCsRunner(driver);;
```

Once after you navigated to any page/popup with Selenium Webdriver execute Accessibility on that particular page/popup
```java
htmlCsRunner.execute();
```

The above `execute` will also generate `JSON Report` on accessibility issues at page/popup level

Once after all the tests executed, you can call the below method to generate consolidated `HTML Report` on accessibility issues
```java
htmlCsRunner.generateHtmlReport();
```

Below is junit example with reporting.

```java
import freemarker.template.TemplateException;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.sridharbandi.HtmlCsRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;

/**
 * A sample test to demonstrate
 */
public class Example {

    private WebDriver driver;
    private static HtmlCsRunner htmlCsRunner;

    @BeforeEach
    public void beforeTest() {
        ChromeDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().window().fullscreen();
        htmlCsRunner = new HtmlCsRunner(driver);

    }

    @AfterEach
    public void tearDown() throws IOException {
        htmlCsRunner.execute();
        driver.quit();
    }

    @AfterAll
    public static void generateReport() throws IOException {
        htmlCsRunner.generateHtmlReport();
    }

    @Test
    public void googleTest() {
        driver.get("https://www.google.com/");
    }

    @Test
    public void stockTest() {
        driver.get("https://www.istockphoto.com/");
    }
}
```

By default, it will check against `WCAG2AA` standards. However, you can configure it to standard you want to test with
```java
htmlCsRunner.setStandard(HTMLCS.WCAG2A);
```

HTML Reports will be generated under `./target/java-a11y/htmlcs` folder.

Below are the report screenshots

Consolidated Report

![Index](/readme/htmlcs_index.png)

Page Report

![Page](/readme/htmlcs_page.png)

#### Using Deque Axe
Create object of `AxeRunner` as below. `driver` will be your WebDriver instance.
```java
AxeRunner axeRunner = new AxeRunner(driver);;
```

Once after you navigated to any page/popup with Selenium Webdriver execute Accessibility on that particular page/popup
```java
axeRunner.execute();
```

The above `execute` will also generate `JSON Report` on accessibility issues at page/popup level

Once after all the tests executed, you can call the below method to generate consolidated `HTML Report` on accessibility issues
```java
axeRunner.generateHtmlReport();
```

Below is junit example with reporting.

```java
import freemarker.template.TemplateException;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.sridharbandi.AxeRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;

/**
 * A sample test to demonstrate
 */
public class Example {

    private WebDriver driver;
    private static AxeRunner axeRunner;

    @BeforeEach
    public void beforeTest() {
        ChromeDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().window().fullscreen();
        axeRunner = new AxeRunner(driver);

    }

    @AfterEach
    public void tearDown() throws IOException {
        axeRunner.execute();
        driver.quit();
    }

    @AfterAll
    public static void generateReport() throws IOException {
        axeRunner.generateHtmlReport();
    }

    @Test
    public void googleTest() {
        driver.get("https://www.google.com/");
    }

    @Test
    public void stockTest() {
        driver.get("https://www.istockphoto.com/");
    }

}
```

HTML Reports will be generated under `./target/java-a11y/axe` folder.

Below are the report screenshots

Consolidated Report

![Index](/readme/axe_index.png)

Page Report

![Page](/readme/axe_page.png)
