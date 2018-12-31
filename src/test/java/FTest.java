import com.accessibility.report.fmarker.FreMarker;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class FTest {

    public static void main(String[] args) throws IOException, TemplateException {
        Configuration cfg = FreMarker.cfg();
        Template template = FreMarker.getTemplate(cfg, "text.ftl");
        Map<String, Object> map = new HashMap<>();
        map.put("blogTitle", "Freemarker Template Demo");
        map.put("message", "Getting started with Freemarker.<br/>Find a simple Freemarker demo.");
        Writer console = new OutputStreamWriter(System.out);
        template.process(map, console);
        console.flush();
    }
}
