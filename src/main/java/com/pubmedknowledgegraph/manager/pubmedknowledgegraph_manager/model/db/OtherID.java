package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys.OtherIDKey;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
public class OtherID implements Persistable<OtherIDKey> {

    @EmbeddedId
    private OtherIDKey otherIDKey;

    public OtherID() {
        this.otherIDKey = new OtherIDKey();
    }

    public String getOtherID() {
        return otherIDKey.getOtherID();
    }

    public void setOtherID(String otherID) {
        this.otherIDKey.setOtherID(otherID);
    }

    public String getSource() {
        return otherIDKey.getSource();
    }

    public void setSource(String source) {
        this.otherIDKey.setSource(source);
    }

    @JsonIgnore
    public Article getArticle() {
        return otherIDKey.getArticle();
    }

    public void setArticle(Article article) {
        this.otherIDKey.setArticle(article);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OtherID otherID = (OtherID) o;

        return otherIDKey.equals(otherID.otherIDKey);
    }

    @Override
    public int hashCode() {
        return otherIDKey.hashCode();
    }

    @Transient
    private boolean isNew = true;

    @JsonIgnore
    @Override
    public OtherIDKey getId() {
        return otherIDKey;
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
