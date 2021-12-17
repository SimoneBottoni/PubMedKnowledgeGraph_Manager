package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.util;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

public class Util {

    private final Logger logger = LogManager.getRootLogger();

    private final String baseUrl = "https://ftp.ncbi.nlm.nih.gov/pubmed/";

    public Util() {
    }

    public String downloadBaseline(String pubMedFolderBaseLine, int fileNumber) {
        String fileName = getFileNameStringFromInt(fileNumber);
        String filePath = pubMedFolderBaseLine + fileName;
        return download(fileName, filePath, baseUrl + "baseline/");
    }

    public String downloadUpdate(String pubMedFolderUpdate, int fileNumber) {
        String fileName = getFileNameStringFromInt(fileNumber);
        String filePath = pubMedFolderUpdate + fileName;
        return download(fileName, filePath, baseUrl + "updatefiles/");
    }

    private String download(String fileName, String filePath, String url) {
        URL urlObj;
        try {
            urlObj = new URL(url + fileName);
            logger.info("Download: " + url + fileName);
            File file = new File(filePath);
            if (!file.exists()) {
                FileUtils.copyURLToFile(urlObj, file);
            }
        } catch (IOException e) {
            return "";
        }
        return filePath;
    }

    public boolean checkUpdateFileWithoutDownloadIt(int fileNumber){
        try {
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection con = (HttpURLConnection)
                    new URL(baseUrl + "updatefiles/" + getFileNameStringFromInt(fileNumber)).openConnection();
            con.setRequestMethod("HEAD");
            return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public File gunZipUpdate(File gzFile) {
        try (GZIPInputStream gzipInputStream = new GZIPInputStream(new FileInputStream(gzFile))) {
            String xmlFileName = gzFile.getPath().substring(0, gzFile.getPath().lastIndexOf("."));
            File xmlFile = new File(xmlFileName);
            if (!xmlFile.exists()) {
                Files.copy(gzipInputStream, xmlFile.toPath());
            }
            return xmlFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getCurrentYear() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy");
        return Integer.parseInt(simpleDateFormat.format(timestamp));
    }

    public int getNumberFromFileName(String file) {
        return Integer.parseInt(file.substring(
                file.indexOf("n")+1,
                file.lastIndexOf(".xml.gz")));
    }

    public String getFileNameStringFromInt(int fileNameInteger) {
        StringBuilder number = new StringBuilder(String.valueOf(fileNameInteger));
        while (number.length() < 4) {
            number.insert(0, "0");
        }
        return "pubmed" + getCurrentYear() + "n" + number.toString() + ".xml.gz";
    }

    public  ArrayList<File> splitXML(File file, String mainPath, String fileName, int numberOfFiles) throws IOException {
        Scanner myReader = new Scanner(file);
        ArrayList<File> AllFiles = new ArrayList<>();

        long lineCount = Files.lines(Paths.get(mainPath + fileName )).count();
        long lineForFile = lineCount / numberOfFiles;

        int i = 0;
        String fileOutputName = mainPath + fileName.split("\\.")[0] + "_part" + (i++) + ".xml";
        AllFiles.add(new File(fileOutputName));
        BufferedWriter fileoutput = new BufferedWriter(new FileWriter(fileOutputName));

        while (! myReader.nextLine().contains("PubmedArticleSet"))
            myReader.nextLine();

        fileoutput.write("<PubmedArticleSet>");
        fileoutput.write(System.getProperty( "line.separator" ));
        int lineC  = 0;

        while (true) {
            if(!myReader.hasNext()) {
                fileoutput.close();
                break;
            }

            lineC ++;
            String line = myReader.nextLine();

            if(line.contains("</PubmedArticle>") && lineC >= lineForFile) {
                fileoutput.write(line);
                fileoutput.write(System.getProperty( "line.separator" ));
                fileoutput.write("</PubmedArticleSet>");
                fileoutput.close();

                fileOutputName = mainPath + fileName.split("\\.")[0] + "_part" + (i++) + ".xml";
                AllFiles.add(new File(fileOutputName));
                fileoutput = new BufferedWriter(new FileWriter(fileOutputName));
                fileoutput.write("<PubmedArticleSet>");
                fileoutput.write(System.getProperty( "line.separator" ));
                lineC  = 0;
            }
            else {
                fileoutput.write(line);
                fileoutput.write(System.getProperty( "line.separator" ));
            }
        }
        return AllFiles;
    }

}
