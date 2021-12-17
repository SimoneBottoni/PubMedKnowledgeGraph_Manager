package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.util.Config;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.integration.config.annotation.EnableBatchIntegration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAdminServer
@EnableJpaRepositories
@EnableScheduling
@EnableBatchProcessing
@EnableBatchIntegration
public class PubMedKnowledgeGraphManagerApplication {

    public static void main(String[] args) {
        Config config = new Config();
        config.initConfig();
        SpringApplication.run(PubMedKnowledgeGraphManagerApplication.class, args);
    }

}
