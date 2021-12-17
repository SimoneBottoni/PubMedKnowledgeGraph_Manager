package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.commandlinerunner;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.metadata.MetaData;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.repository.MetaDataRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class InitialConfig implements CommandLineRunner {

    private final Logger logger = LogManager.getRootLogger();

    @Value("${data.baseline.last.file}")
    private String lastBaseLineFile = "pubmed21n1062.xml.gz";

    @Value("${annotation.during.parsing.enable}")
    private String annotationEnabled = "false";

    private final MetaDataRepository metaDataRepository;

    @Autowired
    public InitialConfig(MetaDataRepository metaDataRepository) {
        this.metaDataRepository = metaDataRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Init MetaData Table.");
        initMetaData();
    }

    public void initMetaData() {
        if (getBaselineStatus() == null) {
            MetaData metaData = new MetaData();
            metaData.setId(1L);
            metaData.setLastBaselineFileComputed("");
            metaData.setLastUpdateFileComputed("");
            metaData.setLastBaselineFile(lastBaseLineFile);
            metaData.setBaseLineEnded("false");
            metaData.setAnnotationEnabled(annotationEnabled);
            metaDataRepository.save(metaData);
        }
    }

    public String getBaselineStatus() {
        return metaDataRepository.getLastBaseLineFileComputed();
    }

}
