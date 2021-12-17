package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.bridge;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys.ArticleJournalKey;
import org.hibernate.search.mapper.pojo.bridge.IdentifierBridge;
import org.hibernate.search.mapper.pojo.bridge.runtime.IdentifierBridgeFromDocumentIdentifierContext;
import org.hibernate.search.mapper.pojo.bridge.runtime.IdentifierBridgeToDocumentIdentifierContext;

public class ArticleJournalKeyBridge implements IdentifierBridge<ArticleJournalKey> {

    @Override
    public String toDocumentIdentifier(ArticleJournalKey articleJournalKey, IdentifierBridgeToDocumentIdentifierContext identifierBridgeToDocumentIdentifierContext) {
        return articleJournalKey.getArticle().getPmid() + "-" +
                articleJournalKey.getArticle().getVersion() + "-" +
                articleJournalKey.getArticle().getUpdate() + "-" +
                articleJournalKey.getJournal().getISOAbbreviation();
    }

    @Override
    public ArticleJournalKey fromDocumentIdentifier(String s, IdentifierBridgeFromDocumentIdentifierContext identifierBridgeFromDocumentIdentifierContext) {
        return null;
    }
}
