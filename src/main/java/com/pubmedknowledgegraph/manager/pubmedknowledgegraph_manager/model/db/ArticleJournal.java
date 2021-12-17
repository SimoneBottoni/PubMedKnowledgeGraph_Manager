package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.bridge.ArticleJournalKeyBridge;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys.ArticleJournalKey;
import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.mapper.pojo.bridge.mapping.annotation.IdentifierBridgeRef;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.DocumentId;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity(name = "article_journal")
@AssociationOverrides({
        @AssociationOverride(name = "articleJournalKey.article",  joinColumns = {
                @JoinColumn(name = "pmid" , referencedColumnName = "pmid"),
                @JoinColumn(name = "version" , referencedColumnName = "version"),
                @JoinColumn(name = "update" , referencedColumnName = "update")
        }),
        @AssociationOverride(name = "articleJournalKey.journal", joinColumns = @JoinColumn(name = "isoabbreviation"))
})
public class ArticleJournal implements Persistable<ArticleJournalKey> {

    @EmbeddedId
    @DocumentId(
            identifierBridge = @IdentifierBridgeRef(type = ArticleJournalKeyBridge.class)
    )
    private ArticleJournalKey articleJournalKey;

    private String Volume;
    private String Issue;

    @GenericField(sortable = Sortable.YES)
    private String PubDate;
    private String CitedMedium;

    public ArticleJournal() {
        this.articleJournalKey = new ArticleJournalKey();
    }

    public void setArticle(Article article) {
        this.articleJournalKey.setArticle(article);
    }

    @JsonIgnore
    public Article getArticle() {
        return articleJournalKey.getArticle();
    }

    public void setJournal(Journal journal) {
        this.articleJournalKey.setJournal(journal);
    }

    @IndexedEmbedded(includeEmbeddedObjectId = true, includeDepth = 1)
    public Journal getJournal() {
        return articleJournalKey.getJournal();
    }

    public String getVolume() {
        return Volume;
    }

    public void setVolume(String volume) {
        Volume = volume;
    }

    public String getIssue() {
        return Issue;
    }

    public void setIssue(String issue) {
        Issue = issue;
    }

    public String getPubDate() {
        return PubDate;
    }

    public void setPubDate(String pubDate) {
        PubDate = pubDate;
    }

    public String getCitedMedium() {
        return CitedMedium;
    }

    public void setCitedMedium(String citedMedium) {
        CitedMedium = citedMedium;
    }

    @Transient
    private boolean isNew = true;

    @JsonIgnore
    @Override
    public ArticleJournalKey getId() {
        return articleJournalKey;
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
