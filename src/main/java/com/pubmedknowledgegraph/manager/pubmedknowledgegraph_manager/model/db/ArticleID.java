package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys.ArticleIDKey;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@SQLInsert(sql="INSERT INTO articleid (article_id, type) values (?, ?) ON CONFLICT DO NOTHING")
public class ArticleID implements Persistable<ArticleIDKey> {

    @EmbeddedId
    private ArticleIDKey articleIDKey;

    public ArticleID() {
        this.articleIDKey = new ArticleIDKey();
    }

    public void setArticleId(String id) {
        this.articleIDKey.setArticleId(id);
    }

    public String getArticleId() {
        return articleIDKey.getArticleId();
    }

    public void setType(String type) {
        this.articleIDKey.setType(type);
    }

    public String getType() {
        return articleIDKey.getType();
    }

    @Override
    public int hashCode() {
        return articleIDKey.hashCode();
    }

    @Transient
    private boolean isNew = true;

    @JsonIgnore
    @Override
    public ArticleIDKey getId() {
        return articleIDKey;
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
