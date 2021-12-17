package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db.Author;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db.Book;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class BookAuthorKey implements Serializable {

    @ManyToOne
    private Book book;

    @ManyToOne
    private Author author;

    private String role;

    public BookAuthorKey() {
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookAuthorKey that = (BookAuthorKey) o;

        if (!author.equals(that.author)) return false;
        return role.equals(that.role);
    }

    @Override
    public int hashCode() {
        int result = author.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }
}
