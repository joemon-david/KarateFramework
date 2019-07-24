package com.web.utils;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;


import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ReportUtility {




    public static void generateReport()
    {

        String path= "target/surefire-reports";
        Collection<File> jsonFiles= FileUtils.listFiles(new File(path),new String[]{"json"},true);
        List<String>jsonPaths= new ArrayList(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
        Configuration config= new Configuration(new File("target"), "MH_API");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths,config);
        reportBuilder.generateReports();
    }
}
