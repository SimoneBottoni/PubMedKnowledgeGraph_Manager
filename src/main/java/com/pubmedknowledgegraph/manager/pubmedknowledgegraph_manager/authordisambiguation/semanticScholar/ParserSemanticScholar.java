package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.authordisambiguation.semanticScholar;

public class ParserSemanticScholar {

    private HandlerSemanticScholar handlerSemanticScholar;

    public ParserSemanticScholar() {
    }

    public HandlerSemanticScholar parse(String path) throws Exception {
        handlerSemanticScholar = new HandlerSemanticScholar();
        handlerSemanticScholar.Listparsing(path);
        return handlerSemanticScholar;
    }

}

