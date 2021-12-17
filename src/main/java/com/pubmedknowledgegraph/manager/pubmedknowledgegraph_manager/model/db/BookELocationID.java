package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys.BookELocationIDKey;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity(name = "book_elocationid")
public class BookELocationID implements Persistable<BookELocationIDKey> {

    @EmbeddedId
    private BookELocationIDKey bookELocationIDKey;

    public BookELocationID() {
        this.bookELocationIDKey = new BookELocationIDKey();
    }

    public String geteLocationID() {
        return bookELocationIDKey.geteLocationID();
    }

    public void seteLocationID(String eLocationID) {
        this.bookELocationIDKey.seteLocationID(eLocationID);
    }

    public String geteIdType() {
        return bookELocationIDKey.geteIdType();
    }

    public void seteIdType(String eIdType) {
        this.bookELocationIDKey.seteIdType(eIdType);
    }

    @JsonIgnore
    public Book getBook() {
        return bookELocationIDKey.getBook();
    }

    public void setBook(Book book) {
        this.bookELocationIDKey.setBook(book);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookELocationID that = (BookELocationID) o;

        return bookELocationIDKey.equals(that.bookELocationIDKey);
    }

    @Override
    public int hashCode() {
        return bookELocationIDKey.hashCode();
    }

    @Transient
    private boolean isNew = true;

    @JsonIgnore
    @Override
    public BookELocationIDKey getId() {
        return bookELocationIDKey;
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
