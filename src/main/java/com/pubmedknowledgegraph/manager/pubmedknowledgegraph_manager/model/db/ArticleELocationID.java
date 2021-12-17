package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys.ArticleELocationIDKey;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity(name = "article_elocationid")
public class ArticleELocationID implements Persistable<ArticleELocationIDKey> {

    @EmbeddedId
    private ArticleELocationIDKey articleELocationIDKey;

    public ArticleELocationID() {
        this.articleELocationIDKey = new ArticleELocationIDKey();
    }

    public String geteLocationID() {
        return articleELocationIDKey.geteLocationID();
    }

    public void seteLocationID(String eLocationID) {
        this.articleELocationIDKey.seteLocationID(eLocationID);
    }

    public String geteIdType() {
        return articleELocationIDKey.geteIdType();
    }

    public void seteIdType(String eIdType) {
        this.articleELocationIDKey.seteIdType(eIdType);
    }

    @JsonIgnore
    public Article getArticle() {
        return articleELocationIDKey.getArticle();
    }

    public void setArticle(Article article) {
        this.articleELocationIDKey.setArticle(article);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleELocationID that = (ArticleELocationID) o;

        return articleELocationIDKey.equals(that.articleELocationIDKey);
    }

    @Override
    public int hashCode() {
        return articleELocationIDKey.hashCode();
    }

    @Transient
    private boolean isNew = true;

    @JsonIgnore
    @Override
    public ArticleELocationIDKey getId() {
        return articleELocationIDKey;
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
