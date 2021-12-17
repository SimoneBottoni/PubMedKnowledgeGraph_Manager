package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.commandlinerunner;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.authordisambiguation.semanticScholar.ParseServiceSemanticScholar;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

/*
@Component
@Order(0)
public class SemanticScholarConfig implements CommandLineRunner {

    private final Logger logger = LogManager.getRootLogger();

    private static final String URLString = "https://s3-us-west-2.amazonaws.com/ai2-s2-research-public/open-corpus/";

    private final WebApplicationContext context;
    private final TaskExecutor taskExecutor;

    private List<String> alreadyInserted;

    public SemanticScholarConfig(WebApplicationContext context, TaskExecutor taskExecutor) {
        this.context = context;
        this.taskExecutor = taskExecutor;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Init DisambiguationAuthor Table - SemanticScholar.");
        File directory = new File("SemanticScholarFiles");
        if(!directory.exists()) directory.mkdir();
        LocalDateTime now = LocalDateTime.now();
        while(!URLExists(now)) {
            now = now.minusDays(1);
        }
        Download(now);

        File[] folderFiles = directory.listFiles();

        if (folderFiles != null) {
            for (File file : folderFiles) {
                if (alreadyInserted.contains(file.getName())) {
                    continue;
                }
                if (file.getName().contains("s2-corpus") && file.getName().contains("Decompressed")) {
                    logger.info("START: " + file.getPath());
                    ParseServiceSemanticScholar parseAnnotateService = (ParseServiceSemanticScholar) context.getBean("parseServiceSemanticScholar");
                    parseAnnotateService.setSemanticScholarFile(file);
                    taskExecutor.execute(parseAnnotateService);
                }
            }
        }
    }

    public boolean URLExists(LocalDateTime localDateTime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dtf.format(localDateTime);
        String url = URLString + date + "/manifest.txt";

        HttpURLConnection urlConnection;
        try {
            urlConnection = (HttpURLConnection) new
                    URL(url).openConnection();
            urlConnection.setRequestMethod("HEAD");
            urlConnection.setConnectTimeout(2000);
            urlConnection.setReadTimeout(2000);
            return (urlConnection.getResponseCode() ==
                    HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            return false;
        }
    }

    private void gunZipUpdate(String filePath) {
        File file = new File( filePath);
        try (GZIPInputStream gis = new GZIPInputStream(new FileInputStream(file))) {
            String outputFile = filePath.replace(".gz", "-Decompressed");
            Files.copy(gis, Paths.get(outputFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Download(LocalDateTime localDateTime) throws IOException {
        //DeleteFiles();
        alreadyInserted = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dtf.format(localDateTime);
        URL url = new URL(URLString + date + "/manifest.txt");
        InputStream is = (InputStream) url.getContent();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line;

        while ((line = br.readLine()) != null && line.contains("corpus")) {
            System.out.println("Download file: " + URLString + date + "/"+ line);
            File newFile = new File("SemanticScholarFiles/" + line);
            if (newFile.exists()) {
                FileUtils.copyURLToFile(
                        new URL(URLString + date + "/" + line),
                        newFile);
                gunZipUpdate(newFile.getPath());
            } else {
                alreadyInserted.add(newFile.getName());
            }
        }

    }

    public void DeleteFiles() {
        File directory = new File("SemanticScholarFiles");
        File[] folderFiles = directory.listFiles();
        if (folderFiles != null) {
            for (File file : folderFiles) {
                if (file.getName().contains("s2-corpus")) {
                    file.delete();
                }
            }
        }
    }
}
 */