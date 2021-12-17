package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.bridge;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys.ArticleTagKey;
import org.hibernate.search.mapper.pojo.bridge.IdentifierBridge;
import org.hibernate.search.mapper.pojo.bridge.runtime.IdentifierBridgeFromDocumentIdentifierContext;
import org.hibernate.search.mapper.pojo.bridge.runtime.IdentifierBridgeToDocumentIdentifierContext;

public class ArticleTagKeyBridge implements IdentifierBridge<ArticleTagKey> {

    @Override
    public String toDocumentIdentifier(ArticleTagKey articleTagKey, IdentifierBridgeToDocumentIdentifierContext identifierBridgeToDocumentIdentifierContext) {
        return articleTagKey.getArticle().getPmid() + "-" +
                articleTagKey.getArticle().getVersion() + "-" +
                articleTagKey.getArticle().getUpdate() + "-" +
                articleTagKey.getTag().getCUI() + "-" +
                articleTagKey.getPosition() + "-" +
                articleTagKey.getScore();
    }

    @Override
    public ArticleTagKey fromDocumentIdentifier(String s, IdentifierBridgeFromDocumentIdentifierContext identifierBridgeFromDocumentIdentifierContext) {
        return null;
    }
}
