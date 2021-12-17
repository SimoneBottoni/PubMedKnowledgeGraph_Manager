package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db.Book;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class LocationLabelKey implements Serializable {

    @Column(columnDefinition="TEXT")
    private String location;

    @ManyToOne
    private Book book;

    public LocationLabelKey() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationLabelKey that = (LocationLabelKey) o;

        return location.equals(that.location);
    }

    @Override
    public int hashCode() {
        return location.hashCode();
    }
}
