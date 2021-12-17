package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@SQLInsert(sql="INSERT INTO suppl_mesh_name (name, type, ui) values (?, ?, ?) ON CONFLICT DO NOTHING")
public class SupplMeshName implements Persistable<String> {

    @Id
    private String UI;

    @Column(length = 1000)
    private String name;
    private String type;

    public SupplMeshName() {
    }

    public String getUI() {
        return UI;
    }

    public void setUI(String UI) {
        this.UI = UI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
