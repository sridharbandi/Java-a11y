package io.github.sridharbandi.ftl;

import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
class FtlConfigTest {
    @InjectMocks
    FtlConfig ftlConfig = FtlConfig.getInstance();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetInstance() {
        Assertions.assertNotEquals(FtlConfig.getInstance(), ftlConfig);
    }

    @Test
    void testGetTemplate() throws IOException {
        Template template = ftlConfig.getTemplate("axe/page.ftl");
        Assertions.assertNotEquals(null, template);
    }

    @Test
    void testInvalidTemplate() {
        Assertions.assertThrows(TemplateNotFoundException.class, () -> ftlConfig.getTemplate("invalid.ftl"));
    }
}