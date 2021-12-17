package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.util;

public class AbstractText {

    private String abstractText;

    private String label;
    private String nlmCategory;

    public AbstractText() {
    }

    public AbstractText(String abstractText, String label, String nlmCategory) {
        this.abstractText = abstractText;
        this.label = label;
        this.nlmCategory = nlmCategory;
    }

    public String getAbstractText() {
        return abstractText;
    }

    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getNlmCategory() {
        return nlmCategory;
    }

    public void setNlmCategory(String nlmCategory) {
        this.nlmCategory = nlmCategory;
    }

}
