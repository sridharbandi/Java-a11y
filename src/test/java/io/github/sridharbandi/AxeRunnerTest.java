package io.github.sridharbandi;

import io.github.sridharbandi.a11y.AxeTag;
import io.github.sridharbandi.a11y.Engine;
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
public class AxeRunnerTest {

    @Mock
    WebDriver driver;
    @InjectMocks
    AxeRunner axeRunner = new AxeRunner(driver);
    @Mock
    A11y a11y;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() throws Exception {
        axeRunner.setPageTile("Page Title")
                .setTags(AxeTag.WCAG21AA)
                .disableRules("link-name")
                .enableRules("autocomplete-valid")
                .execute();
        verify(a11y).execute(any(Engine.class), any(Params.class));
    }
}
