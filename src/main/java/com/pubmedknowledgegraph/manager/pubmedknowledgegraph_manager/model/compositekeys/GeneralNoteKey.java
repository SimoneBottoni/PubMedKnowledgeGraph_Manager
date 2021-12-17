package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db.Article;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class GeneralNoteKey implements Serializable {

    private String owner;

    @Column(columnDefinition="TEXT")
    private String note;

    @ManyToOne
    private Article article;

    public GeneralNoteKey() {
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

        GeneralNoteKey that = (GeneralNoteKey) o;

        if (!owner.equals(that.owner)) return false;
        return note.equals(that.note);
    }

    @Override
    public int hashCode() {
        int result = owner.hashCode();
        result = 31 * result + note.hashCode();
        return result;
    }
}
