import com.a11y.accessibility.AccessibilityRunner;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class Test {
    private WebDriver driver;
    private AccessibilityRunner accessibilityRunner;

    @Before
    public void beforeTest() {
        ChromeDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        chromeOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        driver = new ChromeDriver(chromeOptions);
        accessibilityRunner = new AccessibilityRunner(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @org.junit.Test
    public void runSnifferTest() throws InterruptedException {
        driver.get("https://www.google.co.uk/");
        driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
        accessibilityRunner.execute("Google");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("ChromeDriver");
        searchBox.submit();
        accessibilityRunner.execute();
        accessibilityRunner.generateHtmlReport();
    }
}
