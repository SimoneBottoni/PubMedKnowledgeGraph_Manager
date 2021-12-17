package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.repository;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys.ArticleKey;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, ArticleKey> {

    @Query("SELECT a.articleKey.pmid FROM Article a")
    List<String> getAllPmids();

    @Query("SELECT a.articleKey.pmid FROM Article a WHERE a.articleKey.pmid=:pmid")
    List<String> getPmidByPmid(@Param("pmid") String pmid);

    @Query("SELECT a.articleKey.pmid FROM Article a WHERE a.articleKey.pmid=:pmid AND a.articleKey.version=:version")
    List<String> getPmidByPmidAndVersion(@Param("pmid") String pmid, @Param("version") String version);

    @Transactional
    @Modifying
    @Query("UPDATE Article a set a.cancellationDate = :cancellationDate WHERE a.articleKey.pmid=:pmid AND a.articleKey.version=:version")
    void updateCancellationDate(@Param("cancellationDate") String cancellationDate, @Param("pmid") String pmid, @Param("version") String version);

    @Query("SELECT a FROM Article a WHERE a.articleKey.pmid=:pmid")
    List<Article> getArticlesByPmid(@Param("pmid") String pmid);

    @Query("SELECT a_t.articleTagKey.article FROM article_tag a_t WHERE a_t.articleTagKey.tag.CUI=:cui")
    List<Article> getArticlesByTag(@Param("cui") String cui);

    @Query("SELECT a_t.articleTagKey.article FROM article_tag a_t WHERE a_t.articleTagKey.tag.preferredName=:name")
    List<Article> getArticlesByTagName(@Param("name") String name);

    List<Article> getArticlesByFileName(@Param("fileName") String fileName);

}
