package io.github.sridharbandi.modal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class IssuesTest {
    @Mock
    List<Issue> issueList;
    @Mock
    Issue issue;
    @InjectMocks
    Issues issues;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetReportID(){
        issues.setReportID("IDOO7");
        Assertions.assertEquals(issues.getReportID(),"IDOO7");
    }

    @Test
    void testGetName(){
        issues.setName("A11y");
        Assertions.assertEquals(issues.getName(),"A11y");
    }

    @Test
    void testGetBrowser(){
        issues.setBrowser("Chrome");
        Assertions.assertEquals(issues.getBrowser(),"Chrome");
    }

    @Test
    void testGetDevice(){
        issues.setDevice("Phone");
        Assertions.assertEquals(issues.getDevice(),"Phone");
    }

    @Test
    void testGetSize(){
        issues.setSize("320X480");
        Assertions.assertEquals(issues.getSize(),"320X480");
    }

    @Test
    void testGetDate(){
        issues.setDate("28 Feb");
        Assertions.assertEquals(issues.getDate(),"28 Feb");
    }

    @Test
    void testGetNotices(){
        issues.setNotices(43);
        Assertions.assertEquals(issues.getNotices(),43);
    }

    @Test
    void testGetRWarnings(){
        issues.setWarnings(53);
        Assertions.assertEquals(issues.getWarnings(),53);
    }

    @Test
    void testGetErrors(){
        issues.setErrors(63);
        Assertions.assertEquals(issues.getErrors(),63);
    }

    @Test
    void testGetStandard(){
        issues.setStandard("WCAG AA");
        Assertions.assertEquals(issues.getStandard(),"WCAG AA");
    }

    @Test
    void testGetUrl(){
        issues.setUrl("http://sridharbandi.github.io");
        Assertions.assertEquals(issues.getUrl(),"http://sridharbandi.github.io");
    }

    @Test
    void testGetIssues(){
        when(issues.getIssues().get(0)).thenReturn(issue);
        Assertions.assertEquals(issues.getIssues().get(0),issue);
    }




}
