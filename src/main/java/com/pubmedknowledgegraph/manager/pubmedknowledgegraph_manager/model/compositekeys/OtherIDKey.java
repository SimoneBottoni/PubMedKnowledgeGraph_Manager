package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db.Article;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class OtherIDKey implements Serializable {

    @Column(length = 1000)
    private String otherID;

    private String source;

    @ManyToOne
    private Article article;

    public OtherIDKey() {
    }

    public String getOtherID() {
        return otherID;
    }

    public void setOtherID(String otherID) {
        this.otherID = otherID;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

        OtherIDKey that = (OtherIDKey) o;

        if (!otherID.equals(that.otherID)) return false;
        return source.equals(that.source);
    }

    @Override
    public int hashCode() {
        int result = otherID.hashCode();
        result = 31 * result + source.hashCode();
        return result;
    }
}
