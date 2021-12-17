package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLInsert;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@SQLInsert(sql="INSERT INTO journal (issn, issnlinking, issntype, title, country, nlm_uniqueid, isoabbreviation) values (?, ?, ?, ?, ?, ?, ?) ON CONFLICT DO NOTHING")
public class Journal implements Persistable<String> {

    private String ISSN;
    private String ISSNLinking;
    private String ISSNType;

    @FullTextField(analyzer = "english")
    @Column(columnDefinition="TEXT")
    private String Title;

    @Id
    @Column(columnDefinition="TEXT")
    private String ISOAbbreviation;

    private String country;
    private String nlmUniqueID;

    public Journal() {
    }

    public String getISSN() {
        return ISSN;
    }

    public void setISSN(String ISSN) {
        this.ISSN = ISSN;
    }

    public String getISSNType() {
        return ISSNType;
    }

    public void setISSNType(String ISSNType) {
        this.ISSNType = ISSNType;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getISOAbbreviation() {
        return ISOAbbreviation;
    }

    public void setISOAbbreviation(String ISOAbbreviation) {
        this.ISOAbbreviation = ISOAbbreviation;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNlmUniqueID() {
        return nlmUniqueID;
    }

    public void setNlmUniqueID(String nlmUniqueID) {
        this.nlmUniqueID = nlmUniqueID;
    }

    public String getISSNLinking() { return ISSNLinking; }

    public void setISSNLinking(String ISSNLinking) {  this.ISSNLinking = ISSNLinking; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Journal journal = (Journal) o;

        return ISOAbbreviation.equals(journal.ISOAbbreviation);
    }

    @Override
    public int hashCode() {
        return ISOAbbreviation.hashCode();
    }

    @Transient
    private boolean isNew = true;

    @JsonIgnore
    @Override
    public String getId() {
        return ISOAbbreviation;
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
