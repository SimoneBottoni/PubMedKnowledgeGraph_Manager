package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity(name = "article_object")
public class ArticleObject implements Persistable<Long> {

    @Id
    @GenericGenerator(name = "agGen", strategy = "enhanced-sequence", parameters = {
            @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled"),
            @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
            @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
    })
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agGen")
    private Long id;

    private String type;
    @Column(columnDefinition="TEXT")
    private String name;
    @Column(columnDefinition="TEXT")
    private String text;

    @ManyToOne
    private Article article;

    public ArticleObject() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @JsonIgnore
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
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
