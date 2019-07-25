package io.github.sridharbandi.ftl;

import freemarker.template.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class FtlConfigTest {
    @Mock
    Configuration cfg;
    @InjectMocks
    FtlConfig ftlConfig = FtlConfig.getInstance();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetInstance() {
        Assertions.assertEquals(FtlConfig.getInstance(), ftlConfig);
    }

    @Test
    void testCfg() {
        Configuration result = ftlConfig.cfg();
        Assertions.assertEquals(cfg, result);
    }
}