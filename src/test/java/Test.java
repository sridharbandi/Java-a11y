import com.a11y.accessibility.AccessibilityRunner;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class Test {
    private WebDriver driver;
   // private Handler handler;
    private AccessibilityRunner accessibilityRunner;

    @Before
    public void beforeTest() throws IOException {
        ChromeDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        chromeOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
         driver = new ChromeDriver(chromeOptions);
       /* ChromeDriverManager.chromedriver().version("2.43").setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        chromeOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        driver = new ChromeDriver(chromeOptions);*/
        //handler = new Handler(driver);
        accessibilityRunner = new AccessibilityRunner(driver);

    }

    /*@After
    public void tearDown(){
        driver.quit();
    }*/

    @org.junit.Test
    public void runSnifferTest() throws IOException, InterruptedException {
        driver.get("https://www.google.co.uk/");
        driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
       // handler.runAccessibility("SS");
        accessibilityRunner.execute("Google");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("ChromeDriver");
        searchBox.submit();
        Thread.sleep(5000);
        accessibilityRunner.execute();
        accessibilityRunner.generateHtmlReport();
    }


}
