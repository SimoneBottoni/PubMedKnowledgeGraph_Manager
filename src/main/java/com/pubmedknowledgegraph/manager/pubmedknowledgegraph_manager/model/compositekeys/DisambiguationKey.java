package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class DisambiguationKey implements Serializable {
    private String pmid;
    private String authorID;

    public String getPmid() {
        return pmid;
    }

    public void setPmid(String pmid) {
        this.pmid = pmid;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }
}
