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
