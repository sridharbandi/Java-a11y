/**
 * Copyright (c) 2019 Sridhar Bandi.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
