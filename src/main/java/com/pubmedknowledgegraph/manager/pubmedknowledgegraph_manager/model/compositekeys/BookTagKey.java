package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db.Book;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db.Tag;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class BookTagKey implements Serializable {

    @ManyToOne
    private Book book;

    @ManyToOne
    private Tag tag;

    private String position;
    private String score = "0";

    public BookTagKey() {
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookTagKey that = (BookTagKey) o;

        if (!tag.equals(that.tag)) return false;
        if (!position.equals(that.position)) return false;
        return score.equals(that.score);
    }

    @Override
    public int hashCode() {
        int result = tag.hashCode();
        result = 31 * result + position.hashCode();
        result = 31 * result + score.hashCode();
        return result;
    }
}
