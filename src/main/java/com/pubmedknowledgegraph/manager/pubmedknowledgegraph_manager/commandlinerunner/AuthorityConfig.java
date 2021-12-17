package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.commandlinerunner;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.authordisambiguation.authority.ParseServiceAuthority;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
/*
@Component
@Order(0)
public class AuthorityConfig implements CommandLineRunner {

    private final Logger logger = LogManager.getRootLogger();

    private final WebApplicationContext context;
    private final TaskExecutor taskExecutor;

    private List<String> alreadyInserted;

    public AuthorityConfig(WebApplicationContext context, TaskExecutor taskExecutor) {
        this.context = context;
        this.taskExecutor = taskExecutor;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Init DisambiguationAuthor Table - Authority.");
        File directory = new File("AuthorityFiles");
        if(!directory.exists()) directory.mkdir();
        download();
        File[] folderFiles = directory.listFiles();
        if (folderFiles != null) {
            for (File file : folderFiles) {
                if (alreadyInserted.contains(file.getName())) {
                    continue;
                }
                if (file.getName().contains(".part") && file.getName().contains("Decompressed")) {
                    logger.info("START: " + file.getPath());
                    ParseServiceAuthority parseAnnotateService = (ParseServiceAuthority) context.getBean("parseServiceAuthority");
                    parseAnnotateService.setAuthorityFile(file);
                    taskExecutor.execute(parseAnnotateService);
                }
            }
        }
    }

    public void download() throws IOException {
        //DeleteFiles();
        alreadyInserted = new ArrayList<>();
        List<String> URLS = new ArrayList<>();
        URLS.add("https://databank.illinois.edu/datafiles/f8ja7/download");
        URLS.add("https://databank.illinois.edu/datafiles/baklo/download");
        URLS.add("https://databank.illinois.edu/datafiles/7owod/download");
        URLS.add("https://databank.illinois.edu/datafiles/13hlh/download");
        URLS.add("https://databank.illinois.edu/datafiles/te6hq/download");
        URLS.add("https://databank.illinois.edu/datafiles/als77/download");
        URLS.add("https://databank.illinois.edu/datafiles/2so2m/download");
        URLS.add("https://databank.illinois.edu/datafiles/d9rd3/download");
        URLS.add("https://databank.illinois.edu/datafiles/t5b9w/download");
        URLS.add("https://databank.illinois.edu/datafiles/i4yxr/download");
        URLS.add("https://databank.illinois.edu/datafiles/fpj7c/download");
        URLS.add("https://databank.illinois.edu/datafiles/40lsw/download");
        URLS.add("https://databank.illinois.edu/datafiles/rssd5/download");
        URLS.add("https://databank.illinois.edu/datafiles/whzi1/download");
        URLS.add("https://databank.illinois.edu/datafiles/wcqdd/download");
        URLS.add("https://databank.illinois.edu/datafiles/ch89z/download");
        URLS.add("https://databank.illinois.edu/datafiles/g2yei/download");
        URLS.add("https://databank.illinois.edu/datafiles/gntsg/download");

        int i = 0;
        for (String url: URLS) {
            String nameFile = "authority2009.part"+(i++)+".gz";
            System.out.println("Download file: " + url);
            File newFile = new File(("AuthorityFiles/" +  nameFile));
            if (!newFile.exists()) {
                FileUtils.copyURLToFile(
                        new URL(url),
                        newFile);
                gunZipUpdate(newFile.getPath());
            } else {
                alreadyInserted.add(newFile.getName().replace(".gz", "-Decompressed"));
            }
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

    public void DeleteFiles() {
        File directory = new File("AuthorityFiles");
        File[] folderFiles = directory.listFiles();
        if (folderFiles != null) {
            for (File file : folderFiles) {
                if (file.getName().contains("authority")) {
                    file.delete();
                }
            }
        }
    }

}
 */