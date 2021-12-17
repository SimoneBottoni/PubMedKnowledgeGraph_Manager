package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys.SectionKey;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
public class Section implements Persistable<SectionKey> {

    @EmbeddedId
    private SectionKey sectionKey;

    @Column(columnDefinition="TEXT")
    private String locationLabel;

    public Section() {
        this.sectionKey = new SectionKey();
    }

    public void setBook(Book book) {
        this.sectionKey.setBook(book);
    }

    @JsonIgnore
    public Book getBook() {
        return sectionKey.getBook();
    }

    public void setTitle(String title) {
        this.sectionKey.setTitle(title);
    }

    public String getTitle() {
        return sectionKey.getTitle();
    }

    public String getLocationLabel() {
        return locationLabel;
    }

    public void setLocationLabel(String locationLabel) {
        this.locationLabel = locationLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Section section = (Section) o;

        return sectionKey.equals(section.sectionKey);
    }

    @Override
    public int hashCode() {
        return sectionKey.hashCode();
    }

    @Transient
    private boolean isNew = true;

    @JsonIgnore
    @Override
    public SectionKey getId() {
        return sectionKey;
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
