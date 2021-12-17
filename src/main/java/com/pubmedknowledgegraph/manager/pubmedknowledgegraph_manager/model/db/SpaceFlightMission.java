package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@SQLInsert(sql="INSERT INTO space_flight_mission (space_flight_mission) values (?) ON CONFLICT DO NOTHING")
public class SpaceFlightMission implements Persistable<String> {

    @Id
    @Column(length = 2048)
    private String spaceFlightMission;

    public SpaceFlightMission() {
    }

    public String getSpaceFlightMission() {
        return spaceFlightMission;
    }

    public void setSpaceFlightMission(String spaceFlightMission) {
        this.spaceFlightMission = spaceFlightMission;
    }

    @Transient
    private boolean isNew = true;

    @JsonIgnore
    @Override
    public String getId() {
        return spaceFlightMission;
    }

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
