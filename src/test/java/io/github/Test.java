/**
 * Copyright (c) 2019 Sridhar Bandi.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.github;

import io.github.sridharbandi.AccessibilityRunner;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class Test {
    private WebDriver driver;
    private static AccessibilityRunner accessibilityRunner;

    @Before
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

    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @AfterClass
    public static void generateReport(){
        accessibilityRunner.generateHtmlReport();
    }


    @org.junit.Test
    public void googleTest() throws InterruptedException {
        driver.get("https://www.google.co.uk/");
        accessibilityRunner.execute("Google");
    }
    @org.junit.Test
    public void w3cschoolsTest() throws InterruptedException {
        driver.get("https://www.w3schools.com/");
        accessibilityRunner.execute();
    }
}
