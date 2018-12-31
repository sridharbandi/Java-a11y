package com.accessibility.report.fmarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;

public class FreMarker {

    private static final String TEMPLATE_DIR = "/ftl/";
    private static final String ENCODING = "UTF-8";

    public static Configuration cfg() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setClassForTemplateLoading(FreMarker.class.getClass(), TEMPLATE_DIR);
        cfg.setDefaultEncoding(ENCODING);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        return cfg;
    }

    public static Template getTemplate(Configuration cfg, String template){
        try {
            return cfg.getTemplate(template);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
