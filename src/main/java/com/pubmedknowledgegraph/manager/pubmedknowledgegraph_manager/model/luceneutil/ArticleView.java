package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.luceneutil;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db.ArticleAuthor;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db.ArticleJournal;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db.ArticleTag;

import java.util.List;

public class ArticleView {

    private String id;
    private String title;
    private String anAbstract;

    private List<ArticleTag> articleTagList;
    private List<ArticleAuthor> articleAuthorList;
    private List<ArticleJournal> articleJournalList;

    public ArticleView() {
    }

    public ArticleView(String id, String title, String anAbstract, List<ArticleTag> articleTagList,
                       List<ArticleAuthor> articleAuthorList, List<ArticleJournal> articleJournalList) {
        this.id = id;
        this.title = title;
        this.anAbstract = anAbstract;
        this.articleTagList = articleTagList;
        this.articleAuthorList = articleAuthorList;
        this.articleJournalList = articleJournalList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnAbstract() {
        return anAbstract;
    }

    public void setAnAbstract(String anAbstract) {
        this.anAbstract = anAbstract;
    }

    public List<ArticleTag> getArticleTagList() {
        return articleTagList;
    }

    public void setArticleTagList(List<ArticleTag> articleTagList) {
        this.articleTagList = articleTagList;
    }

    public List<ArticleAuthor> getArticleAuthorList() {
        return articleAuthorList;
    }

    public void setArticleAuthorList(List<ArticleAuthor> articleAuthorList) {
        this.articleAuthorList = articleAuthorList;
    }

    public List<ArticleJournal> getArticleJournalList() {
        return articleJournalList;
    }

    public void setArticleJournalList(List<ArticleJournal> articleJournalList) {
        this.articleJournalList = articleJournalList;
    }
}
