package com.a11y.accessibility.htmlcs;

import com.a11y.accessibility.util.Statik;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HTMLCS {
    private static HTMLCS instance = null;

    public String htmlcs;

    private HTMLCS(){
        try {
            Path path = Paths.get(getClass().getResource(Statik.HTMLCS_PATH).toURI());
            htmlcs = new String(Files.readAllBytes(path));
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    public static HTMLCS getInstance(){
        if (instance == null)
            instance = new HTMLCS();
        return instance;
    }
}
