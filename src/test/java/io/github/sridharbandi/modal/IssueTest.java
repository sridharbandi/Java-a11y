package io.github.sridharbandi.modal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class IssueTest {
    @Mock
    List<String> issueTechniques;


    @InjectMocks
    Issue issue;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetIssueTechniques(){
        when(issue.getIssueTechniques().get(0)).thenReturn("TECH");
        Assertions.assertEquals(issueTechniques.get(0), "TECH");
    }

    @Test
    void testGetIssueType(){
        issue.setIssueType("ERROR");
        Assertions.assertEquals(issue.getIssueType(), "ERROR");
    }

    @Test
    void testGetIssueCode(){
        issue.setIssueCode("H91");
        Assertions.assertEquals(issue.getIssueCode(), "H91");
    }

    @Test
    void testGetIssueTag(){
        issue.setIssueTag("INPUT");
        Assertions.assertEquals(issue.getIssueTag(), "INPUT");
    }

    @Test
    void testGetIssueId(){
        issue.setIssueId("123456");
        Assertions.assertEquals(issue.getIssueId(), "123456");
    }

    @Test
    void testGetIssueMsg(){
        issue.setIssueMsg("No Arial Label");
        Assertions.assertEquals(issue.getIssueMsg(), "No Arial Label");
    }

    @Test
    void testGetIssueElement(){
        issue.setIssueElement("<span>Java a11y</span>");
        Assertions.assertEquals(issue.getIssueElement(), "<span>Java a11y</span>");
    }

}