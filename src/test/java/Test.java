import com.accessibility.handler.Handler;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class Test {
    private WebDriver driver;
    private Handler handler;

    @Before
    public void beforeTest() throws IOException {
        ChromeDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        chromeOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        driver = new ChromeDriver(chromeOptions);
        handler = new Handler(driver);

    }

    @org.junit.Test
    public void runSnifferTest() throws IOException {
        driver.get("http://www.google.com");
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.MILLISECONDS);
        handler.runAccessibility("SS");



    }
}
