package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.util;

public class DeleteCitation {

    private String pmid;
    private String version;

    public DeleteCitation() {
    }

    public String getPmid() {
        return pmid;
    }

    public void setPmid(String pmid) {
        this.pmid = pmid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
