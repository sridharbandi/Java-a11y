import com.accessibility.report.fmarker.FreMarker;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FTest {

    public static void main(String[] args) throws IOException, TemplateException {
        Configuration cfg = FreMarker.cfg();
        Template template = FreMarker.getTemplate(cfg, "chart.ftl");
        Map<String, Object> map = new HashMap<>();
        List<String> items = new ArrayList<>();
        items.add("One");
        items.add("Two");
        map.put("items", items);
        Writer console = new OutputStreamWriter(System.out);
        template.process(map, console);
        console.flush();
    }
}
