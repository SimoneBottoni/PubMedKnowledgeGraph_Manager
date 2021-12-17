package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db.Article;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class CitationSubsetKey implements Serializable {

    @Column(length = 4096)
    private String citationSubset;

    @ManyToOne
    private Article article;

    public CitationSubsetKey() {
    }

    public String getCitationSubset() {
        return citationSubset;
    }

    public void setCitationSubset(String citationSubset) {
        this.citationSubset = citationSubset;
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

        CitationSubsetKey that = (CitationSubsetKey) o;

        return citationSubset.equals(that.citationSubset);
    }

    @Override
    public int hashCode() {
        return citationSubset.hashCode();
    }
}
