package io.github.sridharbandi;

import io.github.sridharbandi.a11y.Engine;
import io.github.sridharbandi.a11y.HTMLCS;
import io.github.sridharbandi.modal.htmlcs.Params;
import io.github.sridharbandi.util.A11y;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openqa.selenium.WebDriver;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class HtmlCsRunnerTest {

    @Mock
    WebDriver driver;
    @InjectMocks
    HtmlCsRunner htmlCsRunner = new HtmlCsRunner(driver);
    @Mock
    A11y a11y;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() throws Exception {
        htmlCsRunner.setStandard(HTMLCS.WCAG2AA);
        String codes[] = {"Code1", "Code2"};
        htmlCsRunner.setIgnoreCodes(codes);
        htmlCsRunner.setPageTile("Page Title").execute();
        verify(a11y).execute(any(Engine.class), any(Params.class));
    }

}
