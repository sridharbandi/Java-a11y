package io.github.sridharbandi.util;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.github.sridharbandi.HtmlCsRunner;
import io.github.sridharbandi.a11y.Engine;
import io.github.sridharbandi.a11y.HTMLCS;
import io.github.sridharbandi.ftl.FtlConfig;
import io.github.sridharbandi.modal.htmlcs.Issue;
import io.github.sridharbandi.modal.htmlcs.Issues;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class A11yTest {

    @InjectMocks
    FtlConfig ftlConfig = FtlConfig.getInstance();
    @Mock
    JavascriptExecutor javascriptExecutor;
    @Mock
    WebDriver driver;
    @Mock
    HtmlCsRunner htmlCsRunner;
    @InjectMocks
    A11y a11y = new A11y(driver);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() throws Exception {
        when(javascriptExecutor.executeScript("return document.readyState")).thenReturn("complete");
        a11y.execute(Engine.HTMLCS, HTMLCS.WCAG2AA.name());
        a11y.jsonReports(Engine.HTMLCS, Issues.class);
        assertTrue(FileUtils.deleteQuietly(Objects.requireNonNull(new File("./target/java-a11y/htmlcs/json").listFiles())[0]));
    }

    @Test
    public void testSave() throws IOException {
        Template template = ftlConfig.getTemplate("test.ftl");
        Map<String, Object> map = new HashMap<>();
        map.put("test", "a11y");
        a11y.save(template, map, "page", Engine.HTMLCS);
        Path path = Paths.get("./target/java-a11y/htmlcs/html/page.html");
        assertTrue(FileUtils.deleteQuietly(path.toFile()));
    }

    @Test
    public void jsonTest() throws IOException, TemplateException, URISyntaxException {
        ChromeDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().window().fullscreen();
        htmlCsRunner = new HtmlCsRunner(driver);
        htmlCsRunner.setStandard(HTMLCS.WCAG2AA);
        driver.get("https://www.google.com/");
        htmlCsRunner.execute("Google Test");
        boolean flag = htmlCsRunner.pageHasErrors("Google Test");
        System.out.println(flag);
        String[] array = {"WCAG2AA.Principle1.Guideline1_3.1_3_1.H49.Center", "WCAG2AA.Principle1.Guideline1_4.1_4_3.G18.Fail", "WCAG2AA.Principle1.Guideline1_3.1_3_1.F68"};
        htmlCsRunner.setCodesToIgnore(array);
        htmlCsRunner.generateHtmlReport();
        driver.quit();
    }

    @Test
    public void hasErrorsTest() throws IOException, TemplateException, URISyntaxException {
        ChromeDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().window().fullscreen();
        htmlCsRunner = new HtmlCsRunner(driver);
        htmlCsRunner.setStandard(HTMLCS.WCAG2AA);
        driver.get("https://www.google.com/");
        htmlCsRunner.execute("Google Test");
        String[] array = {"WCAG2AA.Principle1.Guideline1_3.1_3_1.H49.Center", "WCAG2AA.Principle1.Guideline1_4.1_4_3.G18.Fail"};
        htmlCsRunner.setCodesToIgnore(array);
        boolean flag = htmlCsRunner.pageHasErrors("Google Test");
        System.out.println(flag);
        assertFalse(flag);
        driver.quit();
    }
}
