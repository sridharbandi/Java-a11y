/**
 * Copyright (c) 2019 Sridhar Bandi.
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.github.sridharbandi.util;

public class Statik {
    //HTMLCS Script
    public final static String HTMLCS_SCRIPT = "var script = document.createElement('script');\n" +
            "script.type = 'text/javascript';\n" +
            "script.src = 'https://squizlabs.github.io/HTML_CodeSniffer/build/HTMLCS.js';\n" +
            "document.head.appendChild(script);";
    //HTMLCS Runner
    public static String RUNNER = "window.HTMLCS_RUNNER.run('%s');";
    //HTMLCS Results
    public static String HTMLCS_RESULTS = "return window.HTMLCS.getMessages().map(processIssue);\n" +
            "function processIssue(issue) {\n" +
            "return {\n" +
            "issueType: issue.type,\n" +
            "issueCode: issue.code,\n" +
            "issueMsg: issue.msg,\n" +
            "issueTag: issue.element.nodeName.toLowerCase(),\n" +
            "issueElement: htmlElement(issue.element)\n" +
            "};\n" +
            "}" +
            "function htmlElement(ele) {\n" +
            "var a = \"\";\n" +
            "if (ele.outerHTML) {\n" +
            "var o = ele.cloneNode(!0);\n" +
            "o.innerHTML = \"...\", a = o.outerHTML\n" +
            "}\n" +
            "return a;\n" +
            "}";
    //Freemarker Templates
    public static final String TEMPLATE_DIR = "ftl";
    //Report Encoding
    public static final String ENCODING = "UTF-8";
}
