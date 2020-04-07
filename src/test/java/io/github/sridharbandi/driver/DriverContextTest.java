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
package io.github.sridharbandi.driver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DriverContextTest {

    @Mock
    JavascriptExecutor javascriptExecutor;
    @Mock
    WebDriver driver;
    @Mock
    RemoteWebDriver remoteWebDriver;
    @InjectMocks
    DriverContext driverContext = new DriverContext(driver);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testPageTitle() {
        when(driver.getTitle()).thenReturn("Java a11y");
        Assertions.assertEquals("Java a11y", driverContext.pageTitle());
    }

    @Test
    void testViewPortWidth() {
        when(javascriptExecutor.executeScript("return window.innerWidth")).thenReturn(320L);
        Assertions.assertEquals(320L, driverContext.viewPortWidth());
    }

    @Test
    void testViewPortHeight() {
        when(javascriptExecutor.executeScript("return window.innerHeight")).thenReturn(480L);
        Assertions.assertEquals(480L, driverContext.viewPortHeight());
    }

    @Test
    void testExecuteScript() {
        when(javascriptExecutor.executeScript("return window.innerHeight")).thenReturn(480L);
        Assertions.assertEquals(480L, driverContext.viewPortHeight());
    }

    @Test
    void testViewPort() {
        when(javascriptExecutor.executeScript("return window.innerHeight")).thenReturn(480L);
        when(javascriptExecutor.executeScript("return window.innerWidth")).thenReturn(320L);
        Assertions.assertEquals(driverContext.viewPortWidth()+" X "+ driverContext.viewPortHeight(), driverContext.viewPort());
    }

    @Test
    void testUrl() {
        when(driver.getCurrentUrl()).thenReturn("https://github.com/sridharbandi");
        Assertions.assertEquals("https://github.com/sridharbandi", driverContext.url());
    }

    @Test
    void testDevice() {
        when(javascriptExecutor.executeScript("return window.innerWidth")).thenReturn(320L);
        Assertions.assertEquals("Phone", driverContext.device());
        when(javascriptExecutor.executeScript("return window.innerWidth")).thenReturn(991L);
        Assertions.assertEquals("Tablet", driverContext.device());
        when(javascriptExecutor.executeScript("return window.innerWidth")).thenReturn(1100L);
        Assertions.assertEquals("Small Laptop", driverContext.device());
        when(javascriptExecutor.executeScript("return window.innerWidth")).thenReturn(1300L);
        Assertions.assertEquals("Laptop/Desktop", driverContext.device());
    }

  /* @Test
    void testBrowserName() {
       when(remoteWebDriver.getCapabilities()).thenReturn(DesiredCapabilities.chrome());
        String result = driverContext.browserName();
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }*/
}
