package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db.Article;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class ArticleHistoryKey implements Serializable {

    private String status;
    private String date;

    @ManyToOne
    private Article article;

    public ArticleHistoryKey() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

        ArticleHistoryKey that = (ArticleHistoryKey) o;

        if (!status.equals(that.status)) return false;
        return date.equals(that.date);
    }

    @Override
    public int hashCode() {
        int result = status.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }
}
