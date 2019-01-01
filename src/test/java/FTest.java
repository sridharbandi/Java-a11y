import com.accessibility.Accessibility;
import com.accessibility.report.Issues;
import com.accessibility.report.fmarker.FreMarker;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FTest {

    public static void main(String[] args) throws IOException, TemplateException {
        Configuration cfg = FreMarker.cfg();
        Template template = FreMarker.getTemplate(cfg, "index.ftl");
        List<Issues> allissues = Files.walk(Paths.get(Accessibility.REPORT_PATH+ "/report/json"))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .filter(file -> FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("json"))
                .map(file -> {
                    ObjectMapper mapper = new ObjectMapper();
                    Issues issues = null;
                    try {
                        issues = mapper.readValue(file, Issues.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return issues;
                })
                .collect(Collectors.toList());

        List<String> urlslist = allissues.stream().map(url -> url.getUrl()).collect(Collectors.toList());
        List<Integer> errorslist = allissues.stream().map(url -> url.getErrors()).collect(Collectors.toList());
        List<Integer> warningslist = allissues.stream().map(url -> url.getWarnings()).collect(Collectors.toList());
        List<Integer> noticeslist = allissues.stream().map(url -> url.getNotices()).collect(Collectors.toList());

        int errorscount = getCount(errorslist);
        int warningscount = getCount(warningslist);
        int noticescount = getCount(noticeslist);
        //for(Issues file : jsonreports){
          //  System.out.println(file.getIssues().get(0).getIssueMsg());
        //}



        Map<String, Object> map = new HashMap<>();

        map.put("reportname", "Accessibility Report");
        map.put("urlcount", urlslist.size());
        map.put("errorscount", errorscount);
        map.put("warningscount", warningscount);
        map.put("noticescount", noticescount);
        map.put("urls", urlslist);
        map.put("errors", errorslist);
        map.put("warnings", warningslist);
        map.put("notices", noticeslist);
        map.put("issues", allissues);
        Writer console = new OutputStreamWriter(System.out);
        template.process(map, console);
        console.flush();
    }

    private static int getCount(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).sum();
    }
}
