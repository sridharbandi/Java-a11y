package com.a11y.accessibility.ftl;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import static com.a11y.accessibility.util.Statik.ENCODING;
import static com.a11y.accessibility.util.Statik.TEMPLATE_DIR;

public class FtlConfig {

    private static FtlConfig instance = null;

    private Configuration cfg;

    public FtlConfig() {
        cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setClassForTemplateLoading(FtlConfig.class.getClass(), TEMPLATE_DIR);
        cfg.setDefaultEncoding(ENCODING);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
    }

    public static FtlConfig getInstance() {
        if (instance == null)
            instance = new FtlConfig();
        return instance;
    }

    public Configuration cfg() {
        return cfg;
    }
}
