package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.authordisambiguation.semanticScholar;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.repository.DisambiguationAuthorRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@Component
@Scope("prototype")
@Transactional
public class ParseServiceSemanticScholar implements Runnable {
    private final DisambiguationAuthorRepository disambiguationAuthorRepository;
    private final Logger logger = LogManager.getRootLogger();
    private HandlerSemanticScholar handlerSemanticScholar;
    private File file;


    @Autowired
    public ParseServiceSemanticScholar(DisambiguationAuthorRepository disambiguationAuthorRepository) {
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

    public void setSemanticScholarFile(File file) {
        this.file = file;
    }

    private void parseFile() throws Exception {
        ParserSemanticScholar parser = new ParserSemanticScholar();
        logger.info("Parsing " + file.getName());
        handlerSemanticScholar = parser.parse(file.getPath());
        logger.info("Parsed.");
    }


    @Transactional(propagation = Propagation.REQUIRED, isolation= Isolation.READ_UNCOMMITTED)
    public void insertAuthors() {
        disambiguationAuthorRepository.saveAll(handlerSemanticScholar.getDisambiguationAuthorsList());
    }


}