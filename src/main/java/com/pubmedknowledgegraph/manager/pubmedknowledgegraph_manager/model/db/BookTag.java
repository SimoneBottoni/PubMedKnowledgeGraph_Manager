package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys.BookTagKey;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity(name = "book_tag")
public class BookTag implements Persistable<BookTagKey> {

    @EmbeddedId
    private BookTagKey bookTagKey;

    @Column(columnDefinition="TEXT")
    private String triggerInfo;
    @Column(columnDefinition="TEXT")
    private String positionalInfo;
    @Column(columnDefinition="TEXT")
    private String treeCodes;

    public BookTag() {
        this.bookTagKey = new BookTagKey();
    }

    public BookTag(Book book, Tag tag, String position, String score, String triggerInfo, String positionalInfo, String treeCodes) {
        this.bookTagKey = new BookTagKey();
        this.bookTagKey.setBook(book);
        this.bookTagKey.setTag(tag);
        this.bookTagKey.setPosition(position);
        this.bookTagKey.setScore(score);
        this.triggerInfo = triggerInfo;
        this.positionalInfo = positionalInfo;
        this.treeCodes = treeCodes;
    }

    public BookTag(Book book, Tag tag, String position, String score, String triggerInfo, String positionalInfo) {
        this.bookTagKey = new BookTagKey();
        this.bookTagKey.setBook(book);
        this.bookTagKey.setTag(tag);
        this.bookTagKey.setPosition(position);
        this.bookTagKey.setScore(score);
        this.triggerInfo = triggerInfo;
        this.positionalInfo = positionalInfo;
    }

    @JsonIgnore
    public Book getBook() {
        return bookTagKey.getBook();
    }

    public void setBook(Book book) {
        this.bookTagKey.setBook(book);
    }

    public Tag getTag() {
        return bookTagKey.getTag();
    }

    public void setTag(Tag tag) {
        this.bookTagKey.setTag(tag);
    }

    public String getPosition() {
        return bookTagKey.getPosition();
    }

    public void setPosition(String position) {
        this.bookTagKey.setPosition(position);
    }

    public String getScore() {
        return bookTagKey.getScore();
    }

    public void setScore(String score) {
        this.bookTagKey.setScore(score);
    }

    public String getTriggerInfo() {
        return triggerInfo;
    }

    public void setTriggerInfo(String triggerInfo) {
        this.triggerInfo = triggerInfo;
    }

    public String getPositionalInfo() {
        return positionalInfo;
    }

    public void setPositionalInfo(String positionalInfo) {
        this.positionalInfo = positionalInfo;
    }

    public String getTreeCodes() {
        return treeCodes;
    }

    public void setTreeCodes(String treeCodes) {
        this.treeCodes = treeCodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookTag bookTag = (BookTag) o;

        return bookTagKey.equals(bookTag.bookTagKey);
    }

    @Override
    public int hashCode() {
        return bookTagKey.hashCode();
    }

    @Transient
    private boolean isNew = true;

    @JsonIgnore
    @Override
    public BookTagKey getId() {
        return bookTagKey;
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
