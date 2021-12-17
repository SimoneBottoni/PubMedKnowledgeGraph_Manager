package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.authordisambiguation.authority;

public class ParserAuthority {

    private HandlerAuthority handlerAuthority;

    public ParserAuthority() {}

    public HandlerAuthority parse(String path) throws Exception {
        handlerAuthority = new HandlerAuthority();
        handlerAuthority.Listparsing(path);
        return handlerAuthority;
    }
}

