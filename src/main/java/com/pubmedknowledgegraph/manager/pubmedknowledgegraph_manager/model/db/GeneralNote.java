package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys.GeneralNoteKey;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
public class GeneralNote implements Persistable<GeneralNoteKey> {

    @EmbeddedId
    private GeneralNoteKey generalNoteKey;

    public GeneralNote() {
        this.generalNoteKey = new GeneralNoteKey();
    }

    public String getOwner() {
        return generalNoteKey.getOwner();
    }

    public void setOwner(String owner) {
        this.generalNoteKey.setOwner(owner);
    }

    public String getNote() {
        return generalNoteKey.getNote();
    }

    public void setNote(String note) {
        this.generalNoteKey.setNote(note);
    }

    @JsonIgnore
    public Article getArticle() {
        return generalNoteKey.getArticle();
    }

    public void setArticle(Article article) {
        this.generalNoteKey.setArticle(article);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GeneralNote that = (GeneralNote) o;

        return generalNoteKey.equals(that.generalNoteKey);
    }

    @Override
    public int hashCode() {
        return generalNoteKey.hashCode();
    }

    @Transient
    private boolean isNew = true;

    @JsonIgnore
    @Override
    public GeneralNoteKey getId() {
        return generalNoteKey;
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
