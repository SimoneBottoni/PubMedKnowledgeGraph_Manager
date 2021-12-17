package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys.IsbnKey;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
public class Isbn implements Persistable<IsbnKey> {

    @EmbeddedId
    private IsbnKey isbnKey;

    public Isbn() {
        this.isbnKey = new IsbnKey();
    }

    public String getIsbn() {
        return isbnKey.getIsbn();
    }

    public void setIsbn(String isbn) {
        this.isbnKey.setIsbn(isbn);
    }

    @JsonIgnore
    public Book getBook() {
        return isbnKey.getBook();
    }

    public void setBook(Book book) {
        this.isbnKey.setBook(book);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Isbn isbn = (Isbn) o;

        return isbnKey.equals(isbn.isbnKey);
    }

    @Override
    public int hashCode() {
        return isbnKey.hashCode();
    }

    @Transient
    private boolean isNew = true;

    @JsonIgnore
    @Override
    public IsbnKey getId() {
        return isbnKey;
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
