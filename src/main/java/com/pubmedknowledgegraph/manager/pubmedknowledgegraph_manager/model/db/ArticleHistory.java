package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys.ArticleHistoryKey;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity(name = "article_history")
public class ArticleHistory implements Persistable<ArticleHistoryKey> {

    @EmbeddedId
    private ArticleHistoryKey articleHistoryKey;

    public ArticleHistory() {
        this.articleHistoryKey = new ArticleHistoryKey();
    }

    public String getStatus() {
        return articleHistoryKey.getStatus();
    }

    public void setStatus(String status) {
        this.articleHistoryKey.setStatus(status);
    }

    public String getDate() {
        return articleHistoryKey.getDate();
    }

    public void setDate(String date) {
        this.articleHistoryKey.setDate(date);
    }

    @JsonIgnore
    public Article getArticle() {
        return articleHistoryKey.getArticle();
    }

    public void setArticle(Article article) {
        this.articleHistoryKey.setArticle(article);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleHistory that = (ArticleHistory) o;

        return articleHistoryKey.equals(that.articleHistoryKey);
    }

    @Override
    public int hashCode() {
        return articleHistoryKey.hashCode();
    }

    @Transient
    private boolean isNew = true;

    @JsonIgnore
    @Override
    public ArticleHistoryKey getId() {
        return articleHistoryKey;
    }

    @JsonIgnore
    @Override
    public boolean isNew() {
        return isNew;
    }

    @PrePersist
    @PostLoad
    void markNotNew() {
        this.isNew = false;
    }
}
