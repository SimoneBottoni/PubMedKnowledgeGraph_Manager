package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.authordisambiguation.authority;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.repository.DisambiguationAuthorRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@Component
@Scope("prototype")
@Transactional
public class ParseServiceAuthority implements Runnable {

    private final DisambiguationAuthorRepository disambiguationAuthorRepository;
    private final Logger logger = LogManager.getRootLogger();
    private HandlerAuthority handlerAuthority;
    private File file;


    @Autowired
    public ParseServiceAuthority(DisambiguationAuthorRepository disambiguationAuthorRepository) {
        this.disambiguationAuthorRepository = disambiguationAuthorRepository;
    }

    @Override
    public void run() {
        try {
            parseFile();
            logger.info("Start data entry.");
            insertAuthors();
            logger.info("Data entry completed.");
        } catch (Exception e) {
            logger.info(e);
        }
    }

    public void setAuthorityFile(File file) {
        this.file = file;
    }


    private void parseFile() throws Exception {
        ParserAuthority parser = new ParserAuthority();
        logger.info("Parsing " + file.getName());
        handlerAuthority = parser.parse(file.getPath());
        logger.info("Parsed.");
    }

    private void insertAuthors() {
        disambiguationAuthorRepository.saveAll(handlerAuthority.getAuthor());
    }

}
