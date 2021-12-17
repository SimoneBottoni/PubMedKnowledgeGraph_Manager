package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.metadata;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MetaData {

    @Id
    private Long id;

    private String lastBaselineFileComputed;
    private String lastUpdateFileComputed;
    private String lastBaselineFile;
    private String baseLineEnded;
    private String annotationEnabled;

    public MetaData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastBaselineFileComputed() {
        return lastBaselineFileComputed;
    }

    public void setLastBaselineFileComputed(String lastBaselineFile) {
        this.lastBaselineFileComputed = lastBaselineFile;
    }

    public String getLastUpdateFileComputed() {
        return lastUpdateFileComputed;
    }

    public void setLastUpdateFileComputed(String lastUpdateFile) {
        this.lastUpdateFileComputed = lastUpdateFile;
    }

    public String getLastBaselineFile() {
        return lastBaselineFile;
    }

    public void setLastBaselineFile(String lastBaselineFile) {
        this.lastBaselineFile = lastBaselineFile;
    }

    public String getBaseLineEnded() {
        return baseLineEnded;
    }

    public void setBaseLineEnded(String baseLineEnded) {
        this.baseLineEnded = baseLineEnded;
    }

    public String getAnnotationEnabled() {
        return annotationEnabled;
    }

    public void setAnnotationEnabled(String annotationEnabled) {
        this.annotationEnabled = annotationEnabled;
    }
}
