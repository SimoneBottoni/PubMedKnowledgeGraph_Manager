package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.luceneutil;

import java.util.List;

public class SearchResponse {

    private List<ArticleView> articleView;
    private long total;

    public SearchResponse() {
    }

    public SearchResponse(List<ArticleView> articleView, long total) {
        this.articleView = articleView;
        this.total = total;
    }

    public List<ArticleView> getArticleView() {
        return articleView;
    }

    public void setArticleView(List<ArticleView> articleView) {
        this.articleView = articleView;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
