package com.accessibility.report;

import com.accessibility.Accessibility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveReport {

    public static void save(String jsonReport) {
        String path = getReportPath();
    }

    private static String getReportPath() {
        String directory = Accessibility.REPORT_PATH + "/report/json";
        Path path = Paths.get(directory);
        try {
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return directory;
    }
}
