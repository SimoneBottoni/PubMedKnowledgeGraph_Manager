package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLInsert;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.Set;

@Entity
@SQLInsert(sql="INSERT INTO author (authorityID, collective_name, fore_name,initials, last_name, semantic_scholarID, suffix, authorID) values (?, ?, ?, ?, ?, ?, ?, ?) ON CONFLICT DO NOTHING")
public class Author implements Persistable<Long> {

    @Id
    private Long authorID;

    @FullTextField(analyzer = "english")
    @Column(length = 1000)
    private String lastName;
    @Column(length = 1000)
    private String foreName;
    private String initials;
    private String suffix;

    private String authorityID;
    private String semanticScholarID;

    @FullTextField(analyzer = "english")
    @Column(columnDefinition="TEXT")
    private String collectiveName;

    @ManyToMany
    @SQLInsert(sql="INSERT INTO author_identifier_list (author_authorid, identifier_list_identifier) values (?, ?) ON CONFLICT DO NOTHING")
    private Set<Identifier> identifierList;
    @ManyToMany
    @SQLInsert(sql="INSERT INTO author_affiliation_list (author_authorid, affiliation_list_id) values (?, ?) ON CONFLICT DO NOTHING")
    private Set<Affiliation> affiliationList;

    public Author() {
    }

    public Long getAuthorID() {
        return authorID;
    }

    public void setAuthorID(Long authorID) {
        this.authorID = authorID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getForeName() {
        return foreName;
    }

    public void setForeName(String foreName) {
        this.foreName = foreName;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getCollectiveName() {
        return collectiveName;
    }

    public void setCollectiveName(String collectiveName) {
        this.collectiveName = collectiveName;
    }

    public Set<Identifier> getIdentifierList() {
        return identifierList;
    }

    public void setIdentifierList(Set<Identifier> identifierList) {
        this.identifierList = identifierList;
    }

    public Set<Affiliation> getAffiliationList() {
        return affiliationList;
    }

    public void setAffiliationList(Set<Affiliation> affiliationList) {
        this.affiliationList = affiliationList;
    }

    public String getAuthorityID() {
        return authorityID;
    }

    public void setAuthorityID(String authorityID) {
        this.authorityID = authorityID;
    }

    public String getSemanticScholarID() {
        return semanticScholarID;
    }

    public void setSemanticScholarID(String semanticScholarID) {
        this.semanticScholarID = semanticScholarID;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (lastName != null ? !lastName.equals(author.lastName) : author.lastName != null) return false;
        if (foreName != null ? !foreName.equals(author.foreName) : author.foreName != null) return false;
        if (initials != null ? !initials.equals(author.initials) : author.initials != null) return false;
        if (suffix != null ? !suffix.equals(author.suffix) : author.suffix != null) return false;
        if (collectiveName != null ? !collectiveName.equals(author.collectiveName) : author.collectiveName != null)
            return false;
        if (identifierList != null ? !identifierList.equals(author.identifierList) : author.identifierList != null)
            return false;
        return affiliationList != null ? affiliationList.equals(author.affiliationList) : author.affiliationList == null;
    }

    @Override
    public int hashCode() {
        int result = lastName != null ? lastName.hashCode() : 0;
        result = 31 * result + (foreName != null ? foreName.hashCode() : 0);
        result = 31 * result + (initials != null ? initials.hashCode() : 0);
        result = 31 * result + (suffix != null ? suffix.hashCode() : 0);
        result = 31 * result + (collectiveName != null ? collectiveName.hashCode() : 0);
        result = 31 * result + (identifierList != null ? identifierList.hashCode() : 0);
        result = 31 * result + (affiliationList != null ? affiliationList.hashCode() : 0);
        return result;
    }

    @Transient
    private boolean isNew = true;

    @JsonIgnore
    @Override
    public Long getId() {
        return authorID;
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
