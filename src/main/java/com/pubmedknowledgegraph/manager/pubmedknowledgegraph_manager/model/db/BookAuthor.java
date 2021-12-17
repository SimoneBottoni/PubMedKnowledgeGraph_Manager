package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys.BookAuthorKey;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity(name = "book_author")
@AssociationOverrides({
        @AssociationOverride(name = "bookAuthorKey.book",  joinColumns = {
                @JoinColumn(name = "pmid" , referencedColumnName = "pmid"),
                @JoinColumn(name = "version" , referencedColumnName = "version"),
                @JoinColumn(name = "update" , referencedColumnName = "update")
        }),
        @AssociationOverride(name = "bookAuthorKey.author", joinColumns = @JoinColumn(name = "authorID"))
})
public class BookAuthor implements Persistable<BookAuthorKey> {

    @EmbeddedId
    private BookAuthorKey bookAuthorKey;

    private String equalContrib;

    public BookAuthor() {
        this.bookAuthorKey = new BookAuthorKey();
    }

    public void setBook(Book book) {
        this.bookAuthorKey.setBook(book);
    }

    @JsonIgnore
    public Book getBook() {
        return bookAuthorKey.getBook();
    }

    public void setAuthor(Author author) {
        this.bookAuthorKey.setAuthor(author);
    }

    public Author getAuthor() {
        return bookAuthorKey.getAuthor();
    }

    public void setRole(String role) {
        this.bookAuthorKey.setRole(role);
    }

    public String getRole() {
        return bookAuthorKey.getRole();
    }

    public String getEqualContrib() {
        return equalContrib;
    }

    public void setEqualContrib(String equalContrib) {
        this.equalContrib = equalContrib;
    }

    @Transient
    private boolean isNew = true;

    @JsonIgnore
    @Override
    public BookAuthorKey getId() {
        return bookAuthorKey;
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
