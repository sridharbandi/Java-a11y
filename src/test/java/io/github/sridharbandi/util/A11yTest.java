package io.github.sridharbandi.util;

import freemarker.template.Template;
import io.github.sridharbandi.a11y.Engine;
import io.github.sridharbandi.a11y.HTMLCS;
import io.github.sridharbandi.ftl.FtlConfig;
import io.github.sridharbandi.modal.htmlcs.Issues;
import io.github.sridharbandi.modal.htmlcs.Params;
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

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
    @InjectMocks
    A11y a11y = new A11y(driver);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() throws Exception {
        when(javascriptExecutor.executeScript("return document.readyState")).thenReturn("complete");
        Params params = new Params();
        params.setStandard(HTMLCS.WCAG2AA.name());
        a11y.execute(Engine.HTMLCS, params);
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
}
