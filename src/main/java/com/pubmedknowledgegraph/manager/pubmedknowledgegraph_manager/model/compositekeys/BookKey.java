package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BookKey implements Serializable {

    private String pmid;
    private String version;
    private String update = "0";

    public BookKey() {
    }

    public BookKey(String pmid, String version, String update) {
        this.pmid = pmid;
        this.version = version;
        this.update = update;
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

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookKey bookKey = (BookKey) o;

        if (!pmid.equals(bookKey.pmid)) return false;
        if (!version.equals(bookKey.version)) return false;
        return update.equals(bookKey.update);
    }

    @Override
    public int hashCode() {
        int result = pmid.hashCode();
        result = 31 * result + version.hashCode();
        result = 31 * result + update.hashCode();
        return result;
    }
}
