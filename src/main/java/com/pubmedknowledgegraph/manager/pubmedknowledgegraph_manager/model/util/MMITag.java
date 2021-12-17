package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.util;

public class MMITag {

    private String CUI;
    private String preferredName;
    private String semanticType;
    private String score;
    private String triggerInfo;
    private String positionalInfo;
    private String treeCodes;

    public MMITag() {
    }

    public MMITag(String CUI, String preferredName, String semanticType, String score) {
        this.CUI = CUI;
        this.preferredName = preferredName;
        this.semanticType = semanticType;
        this.score = score;
    }

    public String getCUI() {
        return CUI;
    }

    public void setCUI(String CUI) {
        this.CUI = CUI;
    }

    public String getPreferredName() {
        return preferredName;
    }

    public void setPreferredName(String preferredName) {
        this.preferredName = preferredName;
    }

    public String getSemanticType() {
        return semanticType;
    }

    public void setSemanticType(String semanticType) {
        this.semanticType = semanticType;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTriggerInfo() {
        return triggerInfo;
    }

    public void setTriggerInfo(String triggerInfo) {
        this.triggerInfo = triggerInfo;
    }

    public String getPositionalInfo() {
        return positionalInfo;
    }

    public void setPositionalInfo(String positionalInfo) {
        this.positionalInfo = positionalInfo;
    }

    public String getTreeCodes() {
        return treeCodes;
    }

    public void setTreeCodes(String treeCodes) {
        this.treeCodes = treeCodes;
    }
}
