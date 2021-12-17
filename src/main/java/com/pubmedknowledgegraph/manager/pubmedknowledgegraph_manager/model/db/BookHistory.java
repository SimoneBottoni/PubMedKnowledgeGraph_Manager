package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys.BookHistoryKey;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity(name = "book_history")
public class BookHistory implements Persistable<BookHistoryKey> {

    @EmbeddedId
    private BookHistoryKey bookHistoryKey;

    public BookHistory() {
        this.bookHistoryKey = new BookHistoryKey();
    }

    public String getStatus() {
        return bookHistoryKey.getStatus();
    }

    public void setStatus(String status) {
        this.bookHistoryKey.setStatus(status);
    }

    public String getDate() {
        return bookHistoryKey.getDate();
    }

    public void setDate(String date) {
        this.bookHistoryKey.setDate(date);
    }

    @JsonIgnore
    public Book getBook() {
        return bookHistoryKey.getBook();
    }

    public void setBook(Book book) {
        this.bookHistoryKey.setBook(book);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookHistory that = (BookHistory) o;

        return bookHistoryKey.equals(that.bookHistoryKey);
    }

    @Override
    public int hashCode() {
        return bookHistoryKey.hashCode();
    }

    @Transient
    private boolean isNew = true;

    @JsonIgnore
    @Override
    public BookHistoryKey getId() {
        return bookHistoryKey;
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
