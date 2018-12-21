import com.accessibility.handler.Handler;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Test {
    private WebDriver driver;
    private Handler handler;

    @Before
    public void beforeTest() throws IOException {
        ChromeDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        handler = new Handler(driver);

    }

    @org.junit.Test
    public void runSnifferTest() throws IOException {
        driver.get("http://www.google.com");
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.MILLISECONDS);
        handler.runAccessibility("SS");



    }
}
