package io.github.sridharbandi;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.github.sridharbandi.a11y.Engine;
import io.github.sridharbandi.ftl.FtlConfig;
import io.github.sridharbandi.modal.axe.Issues;
import io.github.sridharbandi.modal.htmlcs.Issue;
import io.github.sridharbandi.util.A11y;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public interface IRunner {
    void execute() throws IOException, URISyntaxException, TemplateException;

    //Only supports HTMLCsRunner Right now.
    default void generateHtmlReport(A11y a11y, Engine engine, Class<?> clazz, String[] issuesToIgnore) throws IOException {
        Template tmplIndex = FtlConfig.getInstance().getTemplate(engine.name().toLowerCase() + "/index.ftl");
        Template tmplPage = FtlConfig.getInstance().getTemplate(engine.name().toLowerCase() + "/page.ftl");
        List<?> reportList = a11y.jsonReports(engine, clazz);

        //Remove all issuesToIgnore if there are any.
        if (issuesToIgnore.length > 0) {
            reportList.forEach(page -> {
                List<Issue> issues = ((io.github.sridharbandi.modal.htmlcs.Issues) page).getResults();
                List<Issue> issuesToRemove = new ArrayList<Issue>();
                AtomicInteger errorsToRemove = new AtomicInteger();
                AtomicInteger warningsToRemove = new AtomicInteger();
                AtomicInteger noticeToRemove = new AtomicInteger();
                issues.forEach(issue -> {
                    for (String issueToIgnore : issuesToIgnore) {
                        if (issue.getCode().contains(issueToIgnore)) {
                            switch (issue.getType()) {
                                case 1:
                                    errorsToRemove.addAndGet(1);
                                    break;
                                case 2:
                                    warningsToRemove.addAndGet(1);
                                    break;
                                default:
                                    noticeToRemove.addAndGet(1);
                            }
                            System.out.println(issue.getType());
                            issuesToRemove.add(issue);
                            break;
                        }
                    }
                });
                issues.removeAll(issuesToRemove);
                ((io.github.sridharbandi.modal.htmlcs.Issues) page).setErrors(((io.github.sridharbandi.modal.htmlcs.Issues)
                        page).getErrors() - errorsToRemove.get());
                ((io.github.sridharbandi.modal.htmlcs.Issues) page).setWarnings(((io.github.sridharbandi.modal.htmlcs
                        .Issues) page).getWarnings() - warningsToRemove.get());
                ((io.github.sridharbandi.modal.htmlcs.Issues) page).setNotices(((io.github.sridharbandi.modal.htmlcs.Issues)
                        page).getNotices() - noticeToRemove.get());
            });
        }

        reportList.forEach(issues -> {
            String id = engine.name().equalsIgnoreCase("axe") ? ((Issues) issues).getId() : ((io.github.sridharbandi.modal.htmlcs.Issues) issues).getId();
            a11y.save(tmplPage, issues, id, engine);
        });

        Map<String, Object> root = new HashMap<>();
        root.put("list", reportList);
        root.put("title", "Accessibility Report");
        a11y.save(tmplIndex, root, "index", engine);
    }

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
