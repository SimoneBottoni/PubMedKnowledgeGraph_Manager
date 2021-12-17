package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys.CitationSubsetKey;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
public class CitationSubset implements Persistable<CitationSubsetKey> {

    @EmbeddedId
    private CitationSubsetKey citationSubsetKey;

    public CitationSubset() {
        this.citationSubsetKey = new CitationSubsetKey();
    }

    public String getCitationSubset() {
        return citationSubsetKey.getCitationSubset();
    }

    public void setCitationSubset(String citationSubset) {
        this.citationSubsetKey.setCitationSubset(citationSubset);
    }

    @JsonIgnore
    public Article getArticle() {
        return citationSubsetKey.getArticle();
    }

    public void setArticle(Article article) {
        this.citationSubsetKey.setArticle(article);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CitationSubset that = (CitationSubset) o;

        return citationSubsetKey.equals(that.citationSubsetKey);
    }

    @Override
    public int hashCode() {
        return citationSubsetKey.hashCode();
    }

    @Transient
    private boolean isNew = true;

    @JsonIgnore
    @Override
    public CitationSubsetKey getId() {
        return citationSubsetKey;
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
