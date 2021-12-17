package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.bridge.ArticleAuthorKeyBridge;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys.ArticleAuthorKey;
import org.hibernate.search.mapper.pojo.bridge.mapping.annotation.IdentifierBridgeRef;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.DocumentId;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity(name = "article_author")
@AssociationOverrides({
        @AssociationOverride(name = "articleAuthorKey.article",  joinColumns = {
                @JoinColumn(name = "pmid" , referencedColumnName = "pmid"),
                @JoinColumn(name = "version" , referencedColumnName = "version"),
                @JoinColumn(name = "update" , referencedColumnName = "update")
        }),
        @AssociationOverride(name = "articleAuthorKey.author", joinColumns = @JoinColumn(name = "authorID"))
})
public class ArticleAuthor implements Persistable<ArticleAuthorKey> {

    @EmbeddedId
    @DocumentId(
            identifierBridge = @IdentifierBridgeRef(type = ArticleAuthorKeyBridge.class)
    )
    private ArticleAuthorKey articleAuthorKey;

    private String equalContrib;

    public ArticleAuthor() {
        this.articleAuthorKey = new ArticleAuthorKey();
    }

    public void setArticle(Article article) {
        this.articleAuthorKey.setArticle(article);
    }

    @JsonIgnore
    public Article getArticle() {
        return articleAuthorKey.getArticle();
    }

    public void setAuthor(Author author) {
        this.articleAuthorKey.setAuthor(author);
    }

    @IndexedEmbedded(includeEmbeddedObjectId = true, includeDepth = 1)
    public Author getAuthor() {
        return articleAuthorKey.getAuthor();
    }

    public void setRole(String role) {
        this.articleAuthorKey.setRole(role);
    }

    public String getRole() {
        return articleAuthorKey.getRole();
    }

    public String getEqualContrib() {
        return equalContrib;
    }

    public void setEqualContrib(String equalContrib) {
        this.equalContrib = equalContrib;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleAuthor that = (ArticleAuthor) o;

        return articleAuthorKey.equals(that.articleAuthorKey);
    }

    @Override
    public int hashCode() {
        return articleAuthorKey.hashCode();
    }

    @Transient
    private boolean isNew = true;

    @JsonIgnore
    @Override
    public ArticleAuthorKey getId() {
        return articleAuthorKey;
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
