package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.util;

import java.util.List;

public class Abstract {

    private List<AbstractText> abstractTextList;
    private String copyrightInformation;

    public Abstract() {
    }

    public List<AbstractText> getAbstractTextList() {
        return abstractTextList;
    }

    public void setAbstractTextList(List<AbstractText> abstractTextList) {
        this.abstractTextList = abstractTextList;
    }

    public String getCopyrightInformation() {
        return copyrightInformation;
    }

    public void setCopyrightInformation(String copyrightInformation) {
        this.copyrightInformation = copyrightInformation;
    }

    public String toString() {
        if (abstractTextList.size() > 1) {
            StringBuilder stringBuilder = new StringBuilder();
            for (AbstractText abstractText : abstractTextList) {
                stringBuilder.append(abstractText.getLabel()).append("|");
                stringBuilder.append(abstractText.getNlmCategory()).append("|");
                stringBuilder.append(abstractText.getAbstractText()).append("\n");
            }
            return stringBuilder.deleteCharAt(stringBuilder.length()-1).toString();
        } else {
            return abstractTextList.get(0).getAbstractText();
        }
    }

}
