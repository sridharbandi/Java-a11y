package com.accessibility.htmlcs;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class HtmlCodeSniffer {

    public static String getJS() throws UnirestException {
       return Unirest.get("http://squizlabs.github.io/HTML_CodeSniffer/build/HTMLCS.js").asString().getBody();
    }
}
