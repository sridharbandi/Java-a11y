package io.github.sridharbandi.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.github.sridharbandi.a11y.Engine;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.nio.file.Files.*;
import static java.nio.file.Paths.get;

public class A11y {

    private static Logger LOG = LoggerFactory.getLogger(A11y.class);

    private WebDriver driver = null;
    private JavascriptExecutor javascriptExecutor = null;

    public A11y(WebDriver driver) {
        this.driver = driver;
        javascriptExecutor = (JavascriptExecutor) driver;
    }

    public A11y(){

    }

    public void execute(Engine engine, String standard) throws URISyntaxException, IOException, TemplateException {
        waitForLoad();
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("js/" + engine.toString().toLowerCase() + ".js");
        String js = IOUtils.toString(in, StandardCharsets.UTF_8);
        String script = engine.name().equalsIgnoreCase("axe") ? "return axeData();" + js : "return getData('" + standard + "');" + js;
        Object issues = javascriptExecutor.executeScript(script);
        ObjectMapper objectMapper = new ObjectMapper();
        String strResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(issues);
        Path path = get("./target/java-a11y/" + engine.toString().toLowerCase() + "/json/" + UUID.randomUUID() + ".json");
        createDirectories(path.getParent());
        write(path, strResponse.getBytes(StandardCharsets.UTF_8));
    }

    private void waitForLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(wd -> javascriptExecutor.executeScript("return document.readyState").equals("complete"));
    }

    public List<?> jsonReports(Engine engine, Class<?> clazz) throws IOException {
        return walk(get("./target/java-a11y/" + engine.toString().toLowerCase() + "/json/"))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .filter(file -> FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("json"))
                .map(file -> {
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        return mapper.readValue(file, clazz);
                    } catch (IOException e) {
                        e.printStackTrace();
                        LOG.error("Failed to read json file {}", file.getAbsolutePath());
                    }
                    return null;
                })
                .collect(Collectors.toList());
    }

    public void save(Template tmpl, Object map, String name, Engine engine) {
        Path path = null;
        File report = null;
        try {
            path = Paths.get("./target/java-a11y/" + engine.toString().toLowerCase() + "/html");
            createDirectories(path);
            report = new File(path + File.separator + name + ".html");
            Writer file = new FileWriter(report);
            if (tmpl == null) {
                throw new IOException();
            }
            tmpl.process(map, file);
            file.flush();
            file.close();
            String loggerMsg = name.equalsIgnoreCase("index") ? "Consoliated " : "Page ";
            LOG.info(loggerMsg + "report generated at " + report.getAbsolutePath());
        } catch (IOException e) {
            LOG.error("unable to write file: " + path + File.separator + name);
            e.printStackTrace();
        } catch (TemplateException e) {
            LOG.error("unable to find template: " + tmpl + " for " + name);
            e.printStackTrace();
        }
    }
}
