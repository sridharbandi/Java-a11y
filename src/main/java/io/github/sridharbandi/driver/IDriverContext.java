package io.github.sridharbandi.driver;

public interface IDriverContext {

    String pageTitle();
    long viewPortWidth();
    long viewPortHeight();
    void executeScript();
    String viewPort();
    String url();
    String device();
    String browserName();
}
