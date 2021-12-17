package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db.Article;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class ArticleELocationIDKey implements Serializable {

    @Column(length = 1000)
    private String eLocationID;
    private String eIdType;

    @ManyToOne
    private Article article;

    public ArticleELocationIDKey() {
    }

    public String geteLocationID() {
        return eLocationID;
    }

    public void seteLocationID(String eLocationID) {
        this.eLocationID = eLocationID;
    }

    public String geteIdType() {
        return eIdType;
    }

    public void seteIdType(String eIdType) {
        this.eIdType = eIdType;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleELocationIDKey that = (ArticleELocationIDKey) o;

        if (!eLocationID.equals(that.eLocationID)) return false;
        return eIdType.equals(that.eIdType);
    }

    @Override
    public int hashCode() {
        int result = eLocationID.hashCode();
        result = 31 * result + eIdType.hashCode();
        return result;
    }
}
