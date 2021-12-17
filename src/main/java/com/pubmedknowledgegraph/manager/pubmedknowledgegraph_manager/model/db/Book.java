package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys.BookKey;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Book implements Persistable<BookKey> {

    @EmbeddedId
    private BookKey bookKey;

    private String fileName;

    private String cancellationDate;

    private String pubDate;
    private String beginningDate;
    private String endingDate;
    private String volume;
    @Column(columnDefinition="TEXT")
    private String volumeTitle;
    private String edition;
    private String medium;
    @Column(columnDefinition="TEXT")
    private String reportNumber;
    @Column(columnDefinition="TEXT")
    private String vernacularTitle;
    private String startPage;
    private String endPage;
    private String medlinePgn;
    @Column(columnDefinition="TEXT")
    private String abstractText;
    @Column(columnDefinition="TEXT")
    private String copyrightInformation;
    private String contributionDate;
    private String dateRevised;
    private String publicationStatus;

    @Column(columnDefinition="TEXT")
    private String bookTitle;
    @Column(columnDefinition="TEXT")
    private String collectionTitle;
    @Column(columnDefinition="TEXT")
    private String articleTitle;

    @ManyToMany
    private List<Publisher> publisherList;
    @ManyToMany
    private Set<Item> itemList;
    @OneToMany(mappedBy = "isbnKey.book", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Isbn> isbnList;
    @OneToMany(mappedBy = "locationLabelKey.book", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LocationLabel> locationLabelList;
    @OneToMany(mappedBy = "sectionKey.book", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Section> sectionList;

    @OneToMany(mappedBy = "bookAuthorKey.book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookAuthor> bookAuthorList;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookGrant> bookGrantList;
    @OneToMany(mappedBy = "bookELocationIDKey.book", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookELocationID> bookELocationIDList;
    @OneToMany(mappedBy = "bookHistoryKey.book", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookHistory> bookHistoryList;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookObject> bookObjectList;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookReference> bookReferenceList;

    @ManyToMany
    private Set<PublicationType> publicationTypeList;
    @ManyToMany
    private Set<Language> languageList;
    @ManyToMany
    private Set<Keyword> keywordList;
    @ManyToMany
    private Set<ArticleID> articleIDList;

    @OneToMany(mappedBy = "bookTagKey.book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookTag> bookTagList;

    public Book() {
        this.bookKey = new BookKey();
    }

    public void setPmid(String pmid) {
        this.bookKey.setPmid(pmid);
    }

    public String getPmid() {
        return bookKey.getPmid();
    }

    public void setVersion(String version) {
        this.bookKey.setVersion(version);
    }

    public String getVersion() {
        return bookKey.getVersion();
    }


    public void setUpdate(String update) {
        this.bookKey.setUpdate(update);
    }

    public String getUpdate() {
        return bookKey.getUpdate();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCancellationDate() {
        return cancellationDate;
    }

    public void setCancellationDate(String cancellationDate) {
        this.cancellationDate = cancellationDate;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getBeginningDate() {
        return beginningDate;
    }

    public void setBeginningDate(String beginningDate) {
        this.beginningDate = beginningDate;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getVolumeTitle() {
        return volumeTitle;
    }

    public void setVolumeTitle(String volumeTitle) {
        this.volumeTitle = volumeTitle;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(String reportNumber) {
        this.reportNumber = reportNumber;
    }

    public String getVernacularTitle() {
        return vernacularTitle;
    }

    public void setVernacularTitle(String vernacularTitle) {
        this.vernacularTitle = vernacularTitle;
    }

    public String getStartPage() {
        return startPage;
    }

    public void setStartPage(String startPage) {
        this.startPage = startPage;
    }

    public String getEndPage() {
        return endPage;
    }

    public void setEndPage(String endPage) {
        this.endPage = endPage;
    }

    public String getMedlinePgn() {
        return medlinePgn;
    }

    public void setMedlinePgn(String medlinePgn) {
        this.medlinePgn = medlinePgn;
    }

    public String getAbstractText() {
        return abstractText;
    }

    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
    }

    public String getCopyrightInformation() {
        return copyrightInformation;
    }

    public void setCopyrightInformation(String copyrightInformation) {
        this.copyrightInformation = copyrightInformation;
    }

    public String getContributionDate() {
        return contributionDate;
    }

    public void setContributionDate(String contributionDate) {
        this.contributionDate = contributionDate;
    }

    public String getDateRevised() {
        return dateRevised;
    }

    public void setDateRevised(String dateRevised) {
        this.dateRevised = dateRevised;
    }

    public String getPublicationStatus() {
        return publicationStatus;
    }

    public void setPublicationStatus(String publicationStatus) {
        this.publicationStatus = publicationStatus;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getCollectionTitle() {
        return collectionTitle;
    }

    public void setCollectionTitle(String collectionTitle) {
        this.collectionTitle = collectionTitle;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public List<Publisher> getPublisherList() {
        return publisherList;
    }

    public void setPublisherList(List<Publisher> publisherList) {
        this.publisherList = publisherList;
    }

    public Set<Item> getItemList() {
        return itemList;
    }

    public void setItemList(Set<Item> itemList) {
        this.itemList = itemList;
    }

    public Set<Isbn> getIsbnList() {
        return isbnList;
    }

    public void setIsbnList(Set<Isbn> isbnList) {
        this.isbnList = isbnList;
    }

    public Set<LocationLabel> getLocationLabelList() {
        return locationLabelList;
    }

    public void setLocationLabelList(Set<LocationLabel> locationLabelList) {
        this.locationLabelList = locationLabelList;
    }

    public Set<Section> getSectionList() {
        return sectionList;
    }

    public void setSectionList(Set<Section> sectionList) {
        this.sectionList = sectionList;
    }

    public List<BookAuthor> getBookAuthorList() {
        return bookAuthorList;
    }

    public List<BookGrant> getBookGrantList() {
        return bookGrantList;
    }

    public void setBookGrantList(List<BookGrant> bookGrantList) {
        this.bookGrantList = bookGrantList;
    }

    public Set<BookELocationID> getBookELocationIDList() {
        return bookELocationIDList;
    }

    public void setBookELocationIDList(Set<BookELocationID> bookELocationIDList) {
        this.bookELocationIDList = bookELocationIDList;
    }

    public Set<BookHistory> getBookHistoryList() {
        return bookHistoryList;
    }

    public void setBookHistoryList(Set<BookHistory> bookHistoryList) {
        this.bookHistoryList = bookHistoryList;
    }

    public List<BookObject> getBookObjectList() {
        return bookObjectList;
    }

    public void setBookObjectList(List<BookObject> bookObjectList) {
        this.bookObjectList = bookObjectList;
    }

    public List<BookReference> getBookReferenceList() {
        return bookReferenceList;
    }

    public void setBookReferenceList(List<BookReference> bookReferenceList) {
        this.bookReferenceList = bookReferenceList;
    }

    public Set<PublicationType> getPublicationTypeList() {
        return publicationTypeList;
    }

    public void setPublicationTypeList(Set<PublicationType> publicationTypeList) {
        this.publicationTypeList = publicationTypeList;
    }

    public Set<Language> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(Set<Language> languageList) {
        this.languageList = languageList;
    }

    public Set<Keyword> getKeywordList() {
        return keywordList;
    }

    public void setKeywordList(Set<Keyword> keywordList) {
        this.keywordList = keywordList;
    }

    public Set<ArticleID> getArticleIDList() {
        return articleIDList;
    }

    public void setArticleIDList(Set<ArticleID> articleIDList) {
        if(this.articleIDList==null) {
            this.articleIDList = articleIDList;
        }
        else {
            this.articleIDList.addAll(articleIDList);
        }
    }

    public void setBookAuthorList(List<BookAuthor> bookAuthorList) {
        this.bookAuthorList = bookAuthorList;
    }

    public List<BookTag> getBookTagList() {
        return bookTagList;
    }

    public void setBookTagList(List<BookTag> bookTagList) {
        if (this.bookTagList == null) {
            this.bookTagList = new ArrayList<>();
        }
        this.bookTagList.addAll(bookTagList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return bookKey.equals(book.bookKey);
    }

    @Override
    public int hashCode() {
        return bookKey.hashCode();
    }

    @Transient
    private boolean isNew = true;

    @Override
    public BookKey getId() {
        return bookKey;
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
