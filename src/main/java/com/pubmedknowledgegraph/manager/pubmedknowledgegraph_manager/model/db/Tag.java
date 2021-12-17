package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLInsert;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@SQLInsert(sql="INSERT INTO tag (preferred_name, semantic_type, cui) values (?, ?, ?) ON CONFLICT DO NOTHING")
public class Tag  implements Persistable<String> {

    @Id
    private String CUI;

    @FullTextField(analyzer = "english")
    @Column(length = 1000)
    private String preferredName;

    private String semanticType;

    public Tag() {
    }

    public Tag(String CUI, String preferredName, String semanticType) {
        this.CUI = CUI;
        this.preferredName = preferredName;
        this.semanticType = semanticType;
    }

    public String getCUI() {
        return CUI;
    }

    public void setCUI(String CUI) {
        this.CUI = CUI;
    }

    public String getPreferredName() {
        return preferredName;
    }

    public void setPreferredName(String preferredName) {
        this.preferredName = preferredName;
    }

    public String getSemanticType() {
        return semanticType;
    }

    public void setSemanticType(String semanticType) {
        this.semanticType = semanticType;
    }

    public String toString() {
        return this.CUI + " | " +
                this.preferredName + " | " +
                this.semanticType;
    }

    @Transient
    private boolean isNew = true;

    @JsonIgnore
    @Override
    public String getId() {
        return CUI;
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
