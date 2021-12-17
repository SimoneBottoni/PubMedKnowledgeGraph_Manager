package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@SQLInsert(sql="INSERT INTO identifier (source, identifier) values (?, ?) ON CONFLICT DO NOTHING")
public class Identifier implements Persistable<String> {

    @Id
    @Column(length = 1000)
    private String identifier;

    private String source;

    public Identifier() {
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Identifier that = (Identifier) o;

        if (!identifier.equals(that.identifier)) return false;
        return source != null ? source.equals(that.source) : that.source == null;
    }

    @Override
    public int hashCode() {
        int result = identifier.hashCode();
        result = 31 * result + (source != null ? source.hashCode() : 0);
        return result;
    }

    @Transient
    private boolean isNew = true;

    @JsonIgnore
    @Override
    public String getId() {
        return identifier;
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
