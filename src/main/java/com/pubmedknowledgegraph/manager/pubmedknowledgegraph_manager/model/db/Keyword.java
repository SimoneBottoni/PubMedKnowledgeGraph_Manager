package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@SQLInsert(sql="INSERT INTO keyword (major_topicyn, owner, keyword) values (?, ?, ?) ON CONFLICT DO NOTHING")
public class Keyword implements Persistable<String> {

    @Id
    @Column(length = 2048)
    private String keyword;

    private String owner;
    private String majorTopicYN;

    public Keyword() {
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getMajorTopicYN() {
        return majorTopicYN;
    }

    public void setMajorTopicYN(String majorTopicYN) {
        this.majorTopicYN = majorTopicYN;
    }

    @Transient
    private boolean isNew = true;

    @JsonIgnore
    @Override
    public String getId() {
        return keyword;
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
