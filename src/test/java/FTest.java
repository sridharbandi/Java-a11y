import com.accessibility.Accessibility;
import com.accessibility.report.Issues;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.TemplateException;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FTest {

    public static void main(String[] args) throws IOException, TemplateException {
      // Configuration cfg = FreMarker.cfg();
       // Template template = FreMarker.getTemplate(cfg, "index.ftl");
        List<Issues> jsonreports = Files.walk(Paths.get(Accessibility.REPORT_PATH+ "/report/json"))
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

        for(Issues file : jsonreports){
            System.out.println(file.getIssues().get(0).getIssueMsg());
        }



       /* Map<String, Object> map = new HashMap<>();
        List<String> items = new ArrayList<>();
        items.add("One");
        items.add("Two");
        map.put("urls", items);
        map.put("errors", items);
        map.put("warnings", items);
        map.put("notices", items);
        Writer console = new OutputStreamWriter(System.out);
        template.process(map, console);
        console.flush();*/
    }
}
