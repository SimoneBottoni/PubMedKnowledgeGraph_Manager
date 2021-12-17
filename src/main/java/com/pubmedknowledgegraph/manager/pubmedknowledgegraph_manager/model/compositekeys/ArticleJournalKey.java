package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db.Article;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db.Journal;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class ArticleJournalKey implements Serializable {

    @ManyToOne
    private Article article;
    @ManyToOne
    private Journal journal;

    public ArticleJournalKey() {
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleJournalKey that = (ArticleJournalKey) o;

        return journal.equals(that.journal);
    }

    @Override
    public int hashCode() {
        return journal.hashCode();
    }
}
