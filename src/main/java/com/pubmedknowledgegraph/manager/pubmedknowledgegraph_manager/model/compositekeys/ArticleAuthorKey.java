package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db.Article;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db.Author;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class ArticleAuthorKey implements Serializable {

    @ManyToOne
    private Article article;

    @ManyToOne
    private Author author;

    private String role;

    public ArticleAuthorKey() {
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleAuthorKey that = (ArticleAuthorKey) o;

        if (!author.equals(that.author)) return false;
        return role.equals(that.role);
    }

    @Override
    public int hashCode() {
        int result = author.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }
}
