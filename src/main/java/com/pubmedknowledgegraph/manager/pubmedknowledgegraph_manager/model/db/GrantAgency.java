package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys.GrantAgencyKey;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@SQLInsert(sql="INSERT INTO grant_agency (acronym, agency, country) values (?, ?, ?) ON CONFLICT DO NOTHING")
public class GrantAgency implements Persistable<GrantAgencyKey> {

    @Column(length = 1000)
    private String acronym;

    @EmbeddedId
    private GrantAgencyKey grantAgencyKey;

    public GrantAgency() {
        this.grantAgencyKey = new GrantAgencyKey();
    }

    public void setAgency(String agency) {
        this.grantAgencyKey.setAgency(agency);
    }

    public String getCountry() {
        return grantAgencyKey.getCountry();
    }

    public void setCountry(String country) {
        this.grantAgencyKey.setCountry(country);
    }

    public String getAgency() {
        return grantAgencyKey.getAgency();
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    @Override
    public int hashCode() {
        return grantAgencyKey.hashCode();
    }

    @Transient
    private boolean isNew = true;

    @JsonIgnore
    @Override
    public GrantAgencyKey getId() {
        return grantAgencyKey;
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
