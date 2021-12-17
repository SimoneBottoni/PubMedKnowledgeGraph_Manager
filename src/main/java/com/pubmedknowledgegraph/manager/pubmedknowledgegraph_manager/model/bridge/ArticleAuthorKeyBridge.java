package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.bridge;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys.ArticleAuthorKey;
import org.hibernate.search.mapper.pojo.bridge.IdentifierBridge;
import org.hibernate.search.mapper.pojo.bridge.runtime.IdentifierBridgeFromDocumentIdentifierContext;
import org.hibernate.search.mapper.pojo.bridge.runtime.IdentifierBridgeToDocumentIdentifierContext;

public class ArticleAuthorKeyBridge implements IdentifierBridge<ArticleAuthorKey> {

    @Override
    public String toDocumentIdentifier(ArticleAuthorKey articleAuthorKey, IdentifierBridgeToDocumentIdentifierContext identifierBridgeToDocumentIdentifierContext) {
        return articleAuthorKey.getArticle().getPmid() + "-" +
                articleAuthorKey.getArticle().getVersion() + "-" +
                articleAuthorKey.getArticle().getUpdate() + "-" +
                articleAuthorKey.getAuthor().getAuthorID() + "-" +
                articleAuthorKey.getRole();
    }

    @Override
    public ArticleAuthorKey fromDocumentIdentifier(String s, IdentifierBridgeFromDocumentIdentifierContext identifierBridgeFromDocumentIdentifierContext) {
        return null;
    }
}
