package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.luceneutil;

public class SearchObject {

    private int pageNumber = 0;
    private int resultsPerPage = 20;
    private String text;
    private String searchType = "";
    private boolean dateFiler = false;
    private String dateFilterTextLow;
    private String dateFilterTextHigh;

    public SearchObject() {
    }

    public SearchObject(int pageNumber, int resultsPerPage, String text, String searchType, boolean dateFiler, String dateFilterTextLow, String dateFilterTextHigh) {
        this.pageNumber = pageNumber;
        this.resultsPerPage = resultsPerPage;
        this.text = text;
        this.searchType = searchType;
        this.dateFiler = dateFiler;
        this.dateFilterTextLow = dateFilterTextLow;
        this.dateFilterTextHigh = dateFilterTextHigh;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getResultsPerPage() {
        return resultsPerPage;
    }

    public void setResultsPerPage(int resultsPerPage) {
        this.resultsPerPage = resultsPerPage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public boolean isDateFiler() {
        return dateFiler;
    }

    public void setDateFiler(boolean dateFiler) {
        this.dateFiler = dateFiler;
    }

    public String getDateFilterTextLow() {
        return dateFilterTextLow;
    }

    public void setDateFilterTextLow(String dateFilterTextLow) {
        this.dateFilterTextLow = dateFilterTextLow;
    }

    public String getDateFilterTextHigh() {
        return dateFilterTextHigh;
    }

    public void setDateFilterTextHigh(String dateFilterTextHigh) {
        this.dateFilterTextHigh = dateFilterTextHigh;
    }
}
