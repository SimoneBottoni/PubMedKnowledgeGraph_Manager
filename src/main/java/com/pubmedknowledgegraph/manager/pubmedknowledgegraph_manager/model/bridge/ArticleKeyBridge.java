package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.bridge;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys.ArticleKey;
import org.hibernate.search.mapper.pojo.bridge.IdentifierBridge;
import org.hibernate.search.mapper.pojo.bridge.runtime.IdentifierBridgeFromDocumentIdentifierContext;
import org.hibernate.search.mapper.pojo.bridge.runtime.IdentifierBridgeToDocumentIdentifierContext;

public class ArticleKeyBridge implements IdentifierBridge<ArticleKey> {


    @Override
    public String toDocumentIdentifier(ArticleKey articleKey, IdentifierBridgeToDocumentIdentifierContext identifierBridgeToDocumentIdentifierContext) {
        return articleKey.getPmid() + "-" + articleKey.getVersion() + "-" + articleKey.getUpdate();
    }

    @Override
    public ArticleKey fromDocumentIdentifier(String s, IdentifierBridgeFromDocumentIdentifierContext identifierBridgeFromDocumentIdentifierContext) {
        String[] split = s.split( "-" );
        ArticleKey articleKey = new ArticleKey();
        articleKey.setPmid(split[0]);
        articleKey.setVersion(split[1]);
        articleKey.setUpdate(split[2]);
        return articleKey;
    }
}
