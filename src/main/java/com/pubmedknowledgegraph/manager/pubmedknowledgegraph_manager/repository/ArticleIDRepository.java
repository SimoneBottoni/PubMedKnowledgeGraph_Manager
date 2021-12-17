package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.repository;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys.ArticleIDKey;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db.ArticleID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleIDRepository extends JpaRepository<ArticleID, ArticleIDKey> {
}
