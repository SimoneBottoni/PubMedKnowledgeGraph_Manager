package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.graph;

public class Edge {

    private final String alias = "r";
    private final String classes = "edge";
    private EdgeData data;
    private final String group = "edges";

    public Edge(EdgeData data) {
        this.data = data;
    }

    public String getAlias() {
        return alias;
    }

    public String getClasses() {
        return classes;
    }

    public EdgeData getData() {
        return data;
    }

    public void setData(EdgeData data) {
        this.data = data;
    }

    public String getGroup() {
        return group;
    }
}
