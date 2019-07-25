package io.github.sridharbandi.htmlcs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class HTMLCSTest {
    @InjectMocks
    HTMLCS htmlcs = HTMLCS.getInstance();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetInstance() {
        HTMLCS result = HTMLCS.getInstance();
        Assertions.assertEquals(htmlcs, result);
    }

    @Test
    void testGetHTMLCS(){
        Assertions.assertTrue(htmlcs.getHTMLCS().contains("HTML_CodeSniffer"));

    }
}