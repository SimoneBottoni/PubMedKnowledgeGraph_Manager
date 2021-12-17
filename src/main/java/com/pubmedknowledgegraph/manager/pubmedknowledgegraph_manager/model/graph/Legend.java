package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.graph;

public class Legend {

    private String borderColor;
    private String tagColor;

    public Legend() {
    }

    public Legend(String borderColor, String tagColor) {
        this.borderColor = borderColor;
        this.tagColor = tagColor;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public String getTagColor() {
        return tagColor;
    }

    public void setTagColor(String tagColor) {
        this.tagColor = tagColor;
    }
}
