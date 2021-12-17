package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "article_reference")
public class ArticleReference implements Persistable<Long> {

    @Id
    @GenericGenerator(name = "arGen", strategy = "enhanced-sequence", parameters = {
            @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled"),
            @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
            @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
    })
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "arGen")
    private Long id;

    @Column(columnDefinition="TEXT")
    private String title;
    @Column(columnDefinition="TEXT")
    private String citation;

    @ManyToOne
    private Article article;

    @ManyToMany
    private Set<ArticleID> articleIDList;

    public ArticleReference() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCitation() {
        return citation;
    }

    public void setCitation(String citation) {
        this.citation = citation;
    }

    @JsonIgnore
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Set<ArticleID> getArticleIDList() {
        return articleIDList;
    }

    public void setArticleIDList(Set<ArticleID> articleIDList) {
        this.articleIDList = articleIDList;
    }

    @Transient
    private boolean isNew = true;

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
