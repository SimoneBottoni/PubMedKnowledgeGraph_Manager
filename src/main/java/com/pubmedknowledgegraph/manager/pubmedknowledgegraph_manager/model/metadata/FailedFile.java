package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.metadata;

import javax.persistence.*;

@Entity
public class FailedFile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String fileName;
    private String type;
    @Column(columnDefinition="TEXT")
    private String exception;

    public FailedFile() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
