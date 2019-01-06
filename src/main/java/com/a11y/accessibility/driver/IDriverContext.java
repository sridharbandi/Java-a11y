package com.a11y.accessibility.driver;

public interface IDriverContext {

    String pageTitle();
    long viewPortWidth();
    long viewPortHeight();
    void executeScript();
}
