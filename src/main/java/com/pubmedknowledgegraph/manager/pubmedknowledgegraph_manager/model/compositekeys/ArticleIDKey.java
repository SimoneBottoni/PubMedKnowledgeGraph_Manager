package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ArticleIDKey implements Serializable {

    @Column(length = 1000)
    private String articleId;

    private String type;

    public ArticleIDKey() {
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String id) {
        this.articleId = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleIDKey that = (ArticleIDKey) o;

        if (!articleId.equals(that.articleId)) return false;
        return type.equals(that.type);
    }

    @Override
    public int hashCode() {
        int result = articleId.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
