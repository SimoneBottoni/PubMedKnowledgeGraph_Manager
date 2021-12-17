package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@SQLInsert(sql="INSERT INTO chemical (name_of_substance, registry_number, ui) values (?, ?, ?) ON CONFLICT DO NOTHING")
public class Chemical implements Persistable<String> {

    @Id
    private String UI;

    private String registryNumber;
    @Column(length = 1000)
    private String nameOfSubstance;

    public Chemical() {
    }

    public String getUI() {
        return UI;
    }

    public void setUI(String UI) {
        this.UI = UI;
    }

    public String getRegistryNumber() {
        return registryNumber;
    }

    public void setRegistryNumber(String registryNumber) {
        this.registryNumber = registryNumber;
    }

    public String getNameOfSubstance() {
        return nameOfSubstance;
    }

    public void setNameOfSubstance(String nameOfSubstance) {
        this.nameOfSubstance = nameOfSubstance;
    }

    @Transient
    private boolean isNew = true;

    @JsonIgnore
    @Override
    public String getId() {
        return UI;
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
