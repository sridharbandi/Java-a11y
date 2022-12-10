package io.github.sridharbandi;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.github.sridharbandi.a11y.Engine;
import io.github.sridharbandi.ftl.FtlConfig;
import io.github.sridharbandi.modal.axe.Issues;
import io.github.sridharbandi.util.A11y;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IRunner {
    Object execute() throws IOException, URISyntaxException, TemplateException;

    default void generateHtmlReport(A11y a11y, Engine engine, Class<?> clazz) throws IOException {
        Template tmplIndex = FtlConfig.getInstance().getTemplate(engine.name().toLowerCase() + "/index.ftl");
        Template tmplPage = FtlConfig.getInstance().getTemplate(engine.name().toLowerCase() + "/page.ftl");
        List<?> issuesList = a11y.jsonReports(engine, clazz);

        issuesList.forEach(issues -> {
            String id = engine.name().equalsIgnoreCase("axe") ? ((Issues) issues).getId() : ((io.github.sridharbandi.modal.htmlcs.Issues) issues).getId();
            a11y.save(tmplPage, issues, id, engine);
        });

        Map<String, Object> root = new HashMap<>();
        root.put("list", issuesList);
        root.put("title", "Accessibility Report");
        a11y.save(tmplIndex, root, "index", engine);
    }
}
