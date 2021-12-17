package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class GrantAgencyKey implements Serializable {

    private String country = "Null";
    @Column(columnDefinition="TEXT")
    private String agency;

    public GrantAgencyKey() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GrantAgencyKey that = (GrantAgencyKey) o;

        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        return agency.equals(that.agency);
    }

    @Override
    public int hashCode() {
        int result = country != null ? country.hashCode() : 0;
        result = 31 * result + agency.hashCode();
        return result;
    }
}
