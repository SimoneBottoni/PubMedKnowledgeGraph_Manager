package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.util;


import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db.*;

import java.util.List;

public class HandlerContainer {

    private List<Article> articleMainList;
    private List<Journal> journalMainList;
    private List<Language> languageMainList;
    private List<AccessionNumber> accessionNumberMainList;
    private List<GrantAgency> grantAgencyMainList;
    private List<PublicationType> publicationTypeMainList;
    private List<Chemical> chemicalMainList;
    private List<SupplMeshName> supplMeshNameMainList;
    private List<GeneSymbol> geneSymbolList;
    private List<MeshHeading> meshHeadingMainList;
    private List<Keyword> keywordMainList;
    private List<SpaceFlightMission> spaceFlightMissionList;
    private List<Author> authorMainList;
    private List<Affiliation> affiliationMainList;
    private List<Identifier> identifierMainList;
    private List<ArticleID> articleIDMainList;
    private List<Book> bookMainList;
    private List<Item> itemMainList;
    private List<Publisher> publisherMainList;
    private List<DeleteCitation> deleteCitationList;

    public HandlerContainer() {
    }

    public List<Article> getArticleMainList() {
        return articleMainList;
    }

    public void setArticleMainList(List<Article> articleMainList) {
        this.articleMainList = articleMainList;
    }

    public List<Journal> getJournalMainList() {
        return journalMainList;
    }

    public void setJournalMainList(List<Journal> journalMainList) {
        this.journalMainList = journalMainList;
    }

    public List<Language> getLanguageMainList() {
        return languageMainList;
    }

    public void setLanguageMainList(List<Language> languageMainList) {
        this.languageMainList = languageMainList;
    }

    public List<AccessionNumber> getAccessionNumberMainList() {
        return accessionNumberMainList;
    }

    public void setAccessionNumberMainList(List<AccessionNumber> accessionNumberMainList) {
        this.accessionNumberMainList = accessionNumberMainList;
    }

    public List<GrantAgency> getGrantAgencyMainList() {
        return grantAgencyMainList;
    }

    public void setGrantAgencyMainList(List<GrantAgency> grantAgencyMainList) {
        this.grantAgencyMainList = grantAgencyMainList;
    }

    public List<PublicationType> getPublicationTypeMainList() {
        return publicationTypeMainList;
    }

    public void setPublicationTypeMainList(List<PublicationType> publicationTypeMainList) {
        this.publicationTypeMainList = publicationTypeMainList;
    }

    public List<Chemical> getChemicalMainList() {
        return chemicalMainList;
    }

    public void setChemicalMainList(List<Chemical> chemicalMainList) {
        this.chemicalMainList = chemicalMainList;
    }

    public List<SupplMeshName> getSupplMeshNameMainList() {
        return supplMeshNameMainList;
    }

    public void setSupplMeshNameMainList(List<SupplMeshName> supplMeshNameMainList) {
        this.supplMeshNameMainList = supplMeshNameMainList;
    }

    public List<GeneSymbol> getGeneSymbolList() {
        return geneSymbolList;
    }

    public void setGeneSymbolList(List<GeneSymbol> geneSymbolList) {
        this.geneSymbolList = geneSymbolList;
    }

    public List<MeshHeading> getMeshHeadingMainList() {
        return meshHeadingMainList;
    }

    public void setMeshHeadingMainList(List<MeshHeading> meshHeadingMainList) {
        this.meshHeadingMainList = meshHeadingMainList;
    }

    public List<Keyword> getKeywordMainList() {
        return keywordMainList;
    }

    public void setKeywordMainList(List<Keyword> keywordMainList) {
        this.keywordMainList = keywordMainList;
    }

    public List<SpaceFlightMission> getSpaceFlightMissionList() {
        return spaceFlightMissionList;
    }

    public void setSpaceFlightMissionList(List<SpaceFlightMission> spaceFlightMissionList) {
        this.spaceFlightMissionList = spaceFlightMissionList;
    }

    public List<Author> getAuthorMainList() {
        return authorMainList;
    }

    public void setAuthorMainList(List<Author> authorMainList) {
        this.authorMainList = authorMainList;
    }

    public List<Affiliation> getAffiliationMainList() {
        return affiliationMainList;
    }

    public void setAffiliationMainList(List<Affiliation> affiliationMainList) {
        this.affiliationMainList = affiliationMainList;
    }

    public List<Identifier> getIdentifierMainList() {
        return identifierMainList;
    }

    public void setIdentifierMainList(List<Identifier> identifierMainList) {
        this.identifierMainList = identifierMainList;
    }

    public List<ArticleID> getArticleIDMainList() {
        return articleIDMainList;
    }

    public void setArticleIDMainList(List<ArticleID> articleIDMainList) {
        this.articleIDMainList = articleIDMainList;
    }

    public List<Book> getBookMainList() {
        return bookMainList;
    }

    public void setBookMainList(List<Book> bookMainList) {
        this.bookMainList = bookMainList;
    }

    public List<Item> getItemMainList() {
        return itemMainList;
    }

    public void setItemMainList(List<Item> itemMainList) {
        this.itemMainList = itemMainList;
    }

    public List<Publisher> getPublisherMainList() {
        return publisherMainList;
    }

    public void setPublisherMainList(List<Publisher> publisherMainList) {
        this.publisherMainList = publisherMainList;
    }

    public List<DeleteCitation> getDeleteCitationList() {
        return deleteCitationList;
    }

    public void setDeleteCitationList(List<DeleteCitation> deleteCitationList) {
        this.deleteCitationList = deleteCitationList;
    }
}
