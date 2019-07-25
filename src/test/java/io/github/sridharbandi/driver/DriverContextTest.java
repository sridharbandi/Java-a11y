package io.github.sridharbandi.driver;

import io.github.sridharbandi.htmlcs.HTMLCS;
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
    HTMLCS htmlcs;
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
