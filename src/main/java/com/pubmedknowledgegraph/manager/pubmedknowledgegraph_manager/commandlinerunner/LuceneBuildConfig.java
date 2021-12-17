package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.commandlinerunner;

import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Component
@Order(2)
public class LuceneBuildConfig implements CommandLineRunner {

    private final EntityManager entityManager;

    @Autowired
    public LuceneBuildConfig(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Start Indexer..");
        SearchSession searchSession = Search.session(entityManager);
        searchSession.massIndexer()
                .idFetchSize(150)
                .batchSizeToLoadObjects(25)
                .threadsToLoadObjects(12)
                .startAndWait();
        System.out.println("Index created.");

    }
}
