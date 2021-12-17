package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.bridge.ArticleKeyBridge;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys.ArticleKey;
import org.hibernate.search.mapper.pojo.automaticindexing.ReindexOnUpdate;
import org.hibernate.search.mapper.pojo.bridge.mapping.annotation.IdentifierBridgeRef;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Indexed
public class Article implements Persistable<ArticleKey> {

    @EmbeddedId
    @DocumentId(
            identifierBridge = @IdentifierBridgeRef(type = ArticleKeyBridge.class)
    )
    private ArticleKey articleKey;

    private String fileName;

    private String cancellationDate;

    private String owner;
    private String status;
    private String versionDate;
    private String indexingMethod;
    private String dateCompleted;
    private String dateRevised;
    private String pubModel;

    @FullTextField(analyzer = "english")
    @Column(columnDefinition="TEXT")
    private String articleTitle;

    private String startPage;
    private String endPage;
    private String medlinePgn;

    @FullTextField(analyzer = "english")
    @Column(columnDefinition="TEXT")
    private String abstractText;

    @Column(columnDefinition="TEXT")
    private String copyrightInformation;
    @Column(columnDefinition="TEXT")
    private String vernacularTitle;
    @Column(columnDefinition="TEXT")
    private String coiStatement;

    private String publicationStatus;

    @IndexedEmbedded(includeEmbeddedObjectId = true, includeDepth = 2,
            includePaths = { "PubDate", "journal.Title"})
    @IndexingDependency(reindexOnUpdate = ReindexOnUpdate.SHALLOW)
    @OneToMany(mappedBy = "articleJournalKey.article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticleJournal> articleJournalList;


    @IndexedEmbedded(includeEmbeddedObjectId = true, includeDepth = 2,
            includePaths = { "author.lastName", "author.collectiveName"})
    @IndexingDependency(reindexOnUpdate = ReindexOnUpdate.SHALLOW)
    @OneToMany(mappedBy = "articleAuthorKey.article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticleAuthor> articleAuthorList;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticleGrant> articleGrantList;

    @OneToMany(mappedBy = "articleELocationIDKey.article", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ArticleELocationID> articleELocationIDList;
    @ManyToMany
    private Set<Language> languageList;
    @ManyToMany
    private Set<SpaceFlightMission> spaceFlightMissionList;
    @ManyToMany
    private Set<GeneSymbol> geneSymbolList;
    @ManyToMany
    private Set<AccessionNumber> accessionNumberList;
    @ManyToMany
    private Set<PublicationType> publicationTypeList;
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticleDate> articleDateList;
    @ManyToMany
    private Set<Chemical> chemicalList;
    @ManyToMany
    private Set<SupplMeshName> supplMeshNameList;
    @OneToMany(mappedBy = "citationSubsetKey.article", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CitationSubset> citationSubsetList;
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentsCorrections> commentsCorrectionsList;
    @ManyToMany
    private Set<MeshHeading> meshHeadingList;
    @OneToMany(mappedBy = "otherIDKey.article", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OtherID> otherIDList;
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OtherAbstract> otherAbstractList;
    @ManyToMany
    private Set<Keyword> keywordList;
    @OneToMany(mappedBy = "generalNoteKey.article", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GeneralNote> generalNoteList;

    @OneToMany(mappedBy = "articleHistoryKey.article", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ArticleHistory> articleHistoryList;
    @ManyToMany
    private Set<ArticleID> articleIDList;
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticleObject> articleObjectList;
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticleReference> articleReferenceList;

    @IndexedEmbedded(includeEmbeddedObjectId = true, includeDepth = 2,
            includePaths = { "tag.preferredName", "tag.CUI"})
    @IndexingDependency(reindexOnUpdate = ReindexOnUpdate.SHALLOW)
    @OneToMany(mappedBy = "articleTagKey.article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticleTag> articleTagList;

    public Article() {
        this.articleKey = new ArticleKey();
    }

    public void setPmid(String pmid) {
        this.articleKey.setPmid(pmid);
    }

    public String getPmid() {
        return articleKey.getPmid();
    }

    public void setVersion(String version) {
        this.articleKey.setVersion(version);
    }

    public String getVersion() {
        return articleKey.getVersion();
    }

    public void setUpdate(String update) {
        this.articleKey.setUpdate(update);
    }

    public String getUpdate() {
        return articleKey.getUpdate();
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVersionDate() {
        return versionDate;
    }

    public void setVersionDate(String versionDate) {
        this.versionDate = versionDate;
    }

    public String getIndexingMethod() {
        return indexingMethod;
    }

    public void setIndexingMethod(String indexingMethod) {
        this.indexingMethod = indexingMethod;
    }

    public String getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(String dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public String getDateRevised() {
        return dateRevised;
    }

    public void setDateRevised(String dateRevised) {
        this.dateRevised = dateRevised;
    }

    public String getPubModel() {
        return pubModel;
    }

    public void setPubModel(String pubModel) {
        this.pubModel = pubModel;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
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

    public String getVernacularTitle() {
        return vernacularTitle;
    }

    public void setVernacularTitle(String vernacularTitle) {
        this.vernacularTitle = vernacularTitle;
    }

    public String getCoiStatement() {
        return coiStatement;
    }

    public void setCoiStatement(String coiStatement) {
        this.coiStatement = coiStatement;
    }

    public String getPublicationStatus() {
        return publicationStatus;
    }

    public void setPublicationStatus(String publicationStatus) {
        this.publicationStatus = publicationStatus;
    }

    public List<ArticleJournal> getArticleJournalList() {
        return articleJournalList;
    }

    public void setArticleJournalList(List<ArticleJournal> articleJournalList) {
        this.articleJournalList = articleJournalList;
    }

    public List<ArticleAuthor> getArticleAuthorList() {
        return articleAuthorList;
    }

    public void setArticleAuthorList(List<ArticleAuthor> articleAuthorList) {
        this.articleAuthorList = articleAuthorList;
    }

    public List<ArticleGrant> getArticleGrantList() {
        return articleGrantList;
    }

    public void setArticleGrantList(List<ArticleGrant> articleGrantList) {
        this.articleGrantList = articleGrantList;
    }

    public Set<ArticleELocationID> getArticleELocationIDList() {
        return articleELocationIDList;
    }

    public void setArticleELocationIDList(Set<ArticleELocationID> articleELocationIDList) {
        this.articleELocationIDList = articleELocationIDList;
    }

    public Set<Language> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(Set<Language> languageList) {
        this.languageList = languageList;
    }

    public Set<SpaceFlightMission> getSpaceFlightMissionList() {
        return spaceFlightMissionList;
    }

    public void setSpaceFlightMissionList(Set<SpaceFlightMission> spaceFlightMissionList) {
        this.spaceFlightMissionList = spaceFlightMissionList;
    }

    public Set<GeneSymbol> getGeneSymbolList() {
        return geneSymbolList;
    }

    public void setGeneSymbolList(Set<GeneSymbol> geneSymbolList) {
        this.geneSymbolList = geneSymbolList;
    }

    public Set<AccessionNumber> getAccessionNumberList() {
        return accessionNumberList;
    }

    public void setAccessionNumberList(Set<AccessionNumber> accessionNumberList) {
        this.accessionNumberList = accessionNumberList;
    }

    public Set<PublicationType> getPublicationTypeList() {
        return publicationTypeList;
    }

    public void setPublicationTypeList(Set<PublicationType> publicationTypeList) {
        this.publicationTypeList = publicationTypeList;
    }

    public List<ArticleDate> getArticleDateList() {
        return articleDateList;
    }

    public void setArticleDateList(List<ArticleDate> articleDateList) {
        this.articleDateList = articleDateList;
    }

    public Set<Chemical> getChemicalList() {
        return chemicalList;
    }

    public void setChemicalList(Set<Chemical> chemicalList) {
        this.chemicalList = chemicalList;
    }

    public Set<SupplMeshName> getSupplMeshNameList() {
        return supplMeshNameList;
    }

    public void setSupplMeshNameList(Set<SupplMeshName> supplMeshNameList) {
        this.supplMeshNameList = supplMeshNameList;
    }

    public Set<CitationSubset> getCitationSubsetList() {
        return citationSubsetList;
    }

    public void setCitationSubsetList(Set<CitationSubset> citationSubsetList) {
        this.citationSubsetList = citationSubsetList;
    }

    public List<CommentsCorrections> getCommentsCorrectionsList() {
        return commentsCorrectionsList;
    }

    public void setCommentsCorrectionsList(List<CommentsCorrections> commentsCorrectionsList) {
        this.commentsCorrectionsList = commentsCorrectionsList;
    }

    public Set<MeshHeading> getMeshHeadingList() {
        return meshHeadingList;
    }

    public void setMeshHeadingList(Set<MeshHeading> meshHeadingList) {
        this.meshHeadingList = meshHeadingList;
    }

    public Set<OtherID> getOtherIDList() {
        return otherIDList;
    }

    public void setOtherIDList(Set<OtherID> otherIDList) {
        this.otherIDList = otherIDList;
    }

    public List<OtherAbstract> getOtherAbstractList() {
        return otherAbstractList;
    }

    public void setOtherAbstractList(List<OtherAbstract> otherAbstractList) {
        this.otherAbstractList = otherAbstractList;
    }

    public Set<Keyword> getKeywordList() {
        return keywordList;
    }

    public void setKeywordList(Set<Keyword> keywordList) {
        this.keywordList = keywordList;
    }

    public Set<GeneralNote> getGeneralNoteList() {
        return generalNoteList;
    }

    public void setGeneralNoteList(Set<GeneralNote> generalNoteList) {
        this.generalNoteList = generalNoteList;
    }

    public Set<ArticleHistory> getArticleHistoryList() {
        return articleHistoryList;
    }

    public void setArticleHistoryList(Set<ArticleHistory> articleHistoryList) {
        this.articleHistoryList = articleHistoryList;
    }

    public Set<ArticleID> getArticleIDList() {
        return articleIDList;
    }

    public void setArticleIDList(Set<ArticleID> articleIDList) {
        this.articleIDList = articleIDList;
    }

    public List<ArticleObject> getArticleObjectList() {
        return articleObjectList;
    }

    public void setArticleObjectList(List<ArticleObject> articleObjectList) {
        this.articleObjectList = articleObjectList;
    }

    public List<ArticleReference> getArticleReferenceList() {
        return articleReferenceList;
    }

    public void setArticleReferenceList(List<ArticleReference> articleReferenceList) {
        this.articleReferenceList = articleReferenceList;
    }

    public List<ArticleTag> getArticleTagList() {
        return articleTagList;
    }

    public void setArticleTagList(List<ArticleTag> articleTagList) {
        if (this.articleTagList == null) {
            this.articleTagList = new ArrayList<>();
        }
        this.articleTagList.addAll(articleTagList);
    }

    public void setArticleTagList(List<ArticleTag> articleTagList, boolean check) {
        this.articleTagList = articleTagList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        return articleKey.equals(article.articleKey);
    }

    @Override
    public int hashCode() {
        return articleKey.hashCode();
    }

    @Transient
    private boolean isNew = true;

    @Override
    public ArticleKey getId() {
        return articleKey;
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
