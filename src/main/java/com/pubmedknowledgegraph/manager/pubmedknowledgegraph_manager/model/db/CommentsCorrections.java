package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
public class CommentsCorrections implements Persistable<Long> {

    @Id
    @GenericGenerator(name = "ccGen", strategy = "enhanced-sequence", parameters = {
            @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled"),
            @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
            @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
    })
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ccGen")
    private Long id;

    @Column(columnDefinition="TEXT")
    private String refSource;
    private String refType;
    @Column(columnDefinition="TEXT")
    private String note;
    private String pmidRef;
    private String pmidRefVersion;

    @ManyToOne
    private Article article;

    public CommentsCorrections() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRefSource() {
        return refSource;
    }

    public void setRefSource(String refSource) {
        this.refSource = refSource;
    }

    public String getRefType() {
        return refType;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPmidRef() {
        return pmidRef;
    }

    public void setPmidRef(String pmidRef) {
        this.pmidRef = pmidRef;
    }

    public String getPmidRefVersion() {
        return pmidRefVersion;
    }

    public void setPmidRefVersion(String pmidRefVersion) {
        this.pmidRefVersion = pmidRefVersion;
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
