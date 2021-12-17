package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@SQLInsert(sql="INSERT INTO gene_symbol (gene_symbol) values (?) ON CONFLICT DO NOTHING")
public class GeneSymbol implements Persistable<String> {

    @Id
    @Column(length = 1000)
    private String geneSymbol;

    public GeneSymbol() {
    }

    public String getGeneSymbol() {
        return geneSymbol;
    }

    public void setGeneSymbol(String geneSymbol) {
        this.geneSymbol = geneSymbol;
    }

    @Transient
    private boolean isNew = true;

    @JsonIgnore
    @Override
    public String getId() {
        return geneSymbol;
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
