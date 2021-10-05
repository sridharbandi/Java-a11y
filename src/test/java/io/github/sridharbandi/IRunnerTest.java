package io.github.sridharbandi;

import io.github.sridharbandi.a11y.Engine;
import io.github.sridharbandi.modal.htmlcs.Issues;
import io.github.sridharbandi.util.A11y;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class IRunnerTest {

    private class RunnerTestImpl implements IRunner {

        @Override
        public void execute() {
            throw new RuntimeException("Not implemented for tests");
        }

        @Override
        public void generateHtmlReport(A11y a11y, Engine engine, Class<?> clazz) throws IOException {
            IRunner.super.generateHtmlReport(a11y, engine, clazz);
        }
    }

    @Test
    public void testGenerateHtmlReport() throws IOException {
        Path source = Paths.get("./src/test/resources/test.json");
        Path target = Paths.get("./target/java-a11y/htmlcs/json/test.json");
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        A11y a11y = new A11y();
        RunnerTestImpl runnerTestImpl = new RunnerTestImpl();
        runnerTestImpl.generateHtmlReport(a11y, Engine.HTMLCS, Issues.class);
        assertTrue(FileUtils.deleteQuietly(new File("./target/java-a11y/htmlcs/json/test.json")));
        assertTrue(FileUtils.deleteQuietly(new File("./target/java-a11y/htmlcs/html/id_javaa11y.html")));
        assertTrue(FileUtils.deleteQuietly(new File("./target/java-a11y/htmlcs/html/index.html")));
    }

}