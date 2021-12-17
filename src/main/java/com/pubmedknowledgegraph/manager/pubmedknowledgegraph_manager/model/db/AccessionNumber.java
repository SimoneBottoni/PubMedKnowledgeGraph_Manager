package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@SQLInsert(sql="INSERT INTO accession_number (data_bank_name, accession_number) values (?, ?) ON CONFLICT DO NOTHING")
public class AccessionNumber implements Persistable<String> {

    @Id
    @Column(length = 1000)
    private String accessionNumber;
    @Column(length = 1000)
    private String dataBankName;

    public AccessionNumber() {
    }

    public String getAccessionNumber() {
        return accessionNumber;
    }

    public void setAccessionNumber(String accessionNumber) {
        this.accessionNumber = accessionNumber;
    }

    public String getDataBankName() {
        return dataBankName;
    }

    public void setDataBankName(String dataBankName) {
        this.dataBankName = dataBankName;
    }

    @JsonIgnore
    @Override
    public String getId() {
        return accessionNumber;
    }

    @Transient
    private boolean isNew = true;

    @JsonIgnore
    @Override
    public boolean isNew() {
        return isNew;
    }

    @PrePersist
    @PostLoad
    void markNotNew() {
        this.isNew = false;
    }
}
