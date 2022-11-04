package io.github.sridharbandi;

import io.github.sridharbandi.a11y.Engine;
import io.github.sridharbandi.util.A11y;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openqa.selenium.WebDriver;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AxeRunnerTest {

    @Mock
    WebDriver driver;
    @InjectMocks
    AxeRunner axeRunner = new AxeRunner(driver);
    @Mock
    A11y a11y;
    @Mock
    IRunner iRunner;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() throws Exception {
        axeRunner.execute();
        verify(a11y).execute(Engine.AXE, null);
    }
}
