package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.graph;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.UUID;

public class NodeData {

    private final String backgroundColor;
    private final String tagColor;

    private String id;
    private String label;

    private String pk;

    private ObjectNode properties;

    public NodeData(String label) {
        this.label = label;
        this.properties = new JsonMapper().createObjectNode();
        this.id = getRandomUUID();
        backgroundColor = Colors.getHexColor(Colors.valueOf(label).toString());
        tagColor = Colors.valueOf(label).toString();
    }

    public void addProperties(String key, String value) {
        properties.put(key, value);
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getTagColor() {
        return tagColor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public ObjectNode getProperties() {
        return properties;
    }

    public void setProperties(ObjectNode properties) {
        this.properties = properties;
    }

    private String getRandomUUID() {
        return String.format("%015d", UUID.randomUUID().toString().hashCode()).replace("-", "");
    }
}
