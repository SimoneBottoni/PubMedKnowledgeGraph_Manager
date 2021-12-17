package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys.LocationLabelKey;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
public class LocationLabel implements Persistable<LocationLabelKey> {

    @EmbeddedId
    private LocationLabelKey locationLabelKey;

    private String type;

    public LocationLabel() {
        this.locationLabelKey = new LocationLabelKey();
    }

    public void setLocation(String location) {
        this.locationLabelKey.setLocation(location);
    }

    public String getLocation() {
        return locationLabelKey.getLocation();
    }

    public String getLocationLabelKeyLocation() {
        return this.locationLabelKey.getLocation();
    }

    public void setBook(Book book) {
        this.locationLabelKey.setBook(book);
    }

    @JsonIgnore
    public Book getBook() {
        return locationLabelKey.getBook();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationLabel that = (LocationLabel) o;

        return locationLabelKey.equals(that.locationLabelKey);
    }

    @Override
    public int hashCode() {
        return locationLabelKey.hashCode();
    }

    @Transient
    private boolean isNew = true;

    @JsonIgnore
    @Override
    public LocationLabelKey getId() {
        return locationLabelKey;
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
