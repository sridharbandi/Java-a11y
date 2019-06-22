package io.github.sridharbandi.util;


import io.github.sridharbandi.Accessibility;
import io.github.sridharbandi.modal.Issues;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveJson {

    private static Logger LOG = LoggerFactory.getLogger(SaveJson.class);

    public static void save(Issues issues, String reportName) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String strResponse = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(issues);
            String path = getReportPath(true) + "/" + reportName + ".json";
            Files.write(Paths.get(path), strResponse.getBytes());
            LOG.info("Saved Accessibility Json Report {} at {}", reportName, path);
            if(Accessibility.LOG_RESULTS){
                LOG.info("Accessibility Json \n"+ strResponse);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            LOG.error("Failed to convert Object to Json " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error("Failed to save json " + e.getMessage());
        }
    }

    public static String getReportPath(boolean isJson) {
        String folder = isJson? "json": "html";
        String directory = Accessibility.REPORT_PATH + "/report/"+folder;
        Path path = Paths.get(directory);
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return directory;
    }
}
