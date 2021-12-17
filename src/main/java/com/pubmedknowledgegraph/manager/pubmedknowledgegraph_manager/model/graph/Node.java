package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.graph;

public class Node {

    private final String alias = "v";
    private final String classes = "node";
    private NodeData data;
    private final String group = "nodes";

    public Node(NodeData data) {
        this.data = data;
    }

    public String getAlias() {
        return alias;
    }

    public String getClasses() {
        return classes;
    }

    public NodeData getData() {
        return data;
    }

    public void setData(NodeData data) {
        this.data = data;
    }

    public String getGroup() {
        return group;
    }
}
