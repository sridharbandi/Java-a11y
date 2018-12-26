package com.accessibility.report;

import com.accessibility.Accessibility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveReport {

    public static void save(String jsonReport, String reportName) {
        String path = getReportPath();
        try {
            Files.write(Paths.get(path+"/"+reportName+".json"),jsonReport.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getReportPath() {
        String directory = Accessibility.REPORT_PATH + "/report/json";
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
