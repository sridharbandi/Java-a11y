package io.github.sridharbandi.htmlcs;

import io.github.sridharbandi.util.Statik;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HTMLCS {

    private static Logger LOG = LoggerFactory.getLogger(HTMLCS.class);

    private static HTMLCS instance = null;

    private String htmlcs;

    private HTMLCS(){
        try {
            Path path = Paths.get(getClass().getResource(Statik.HTMLCS_PATH).toURI());
            htmlcs = new String(Files.readAllBytes(path));
        } catch (URISyntaxException | IOException e) {
            LOG.error("Failed to read the file HTMLCS.js %s", e.getMessage());
            e.printStackTrace();
        }
    }

    public static HTMLCS getInstance(){
        if (instance == null)
            instance = new HTMLCS();
        return instance;
    }

    public String getHTMLCS(){
        return htmlcs;
    }
}
