package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.Set;

@Entity
@SQLInsert(sql="INSERT INTO affiliation (affiliation, id) values (?, ?) ON CONFLICT DO NOTHING")
public class Affiliation implements Persistable<Long> {

    @Id
    private Long id;

    @Column(columnDefinition="TEXT")
    private String affiliation;

    @ManyToMany
    @SQLInsert(sql="INSERT INTO affiliation_identifier_list (affiliation_id, identifier_list_identifier) values (?, ?) ON CONFLICT DO NOTHING")
    private Set<Identifier> identifierList;

    public Affiliation() {
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public Set<Identifier> getIdentifierList() {
        return identifierList;
    }

    public void setIdentifierList(Set<Identifier> identifierList) {
        this.identifierList = identifierList;
    }

    @JsonIgnore
    @Override
    public Long getId() {
        return id;
    }

    public void setId() {
        this.id = (long) this.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Affiliation that = (Affiliation) o;

        if (!affiliation.equals(that.affiliation)) return false;
        return identifierList != null ? identifierList.equals(that.identifierList) : that.identifierList == null;
    }

    @Override
    public int hashCode() {
        int result = affiliation.hashCode();
        result = 31 * result + (identifierList != null ? identifierList.hashCode() : 0);
        return result;
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
