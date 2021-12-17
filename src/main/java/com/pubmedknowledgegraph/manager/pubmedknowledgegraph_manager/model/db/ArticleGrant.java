package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity(name = "article_grant_agency")
public class ArticleGrant implements Persistable<Long> {

    @Id
    @GenericGenerator(name = "agaGen", strategy = "enhanced-sequence", parameters = {
            @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled"),
            @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
            @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
    })
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agaGen")
    private Long id;

    @ManyToOne
    private GrantAgency grantAgency;

    @ManyToOne
    private Article article;

    @Column(columnDefinition="TEXT")
    private String grantID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GrantAgency getGrantAgency() {
        return grantAgency;
    }

    public void setGrantAgency(GrantAgency grantAgency) {
        this.grantAgency = grantAgency;
    }

    @JsonIgnore
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getGrantID() {
        return grantID;
    }

    public void setGrantID(String grantID) {
        this.grantID = grantID;
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
