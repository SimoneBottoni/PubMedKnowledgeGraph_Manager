package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.repository;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys.GrantAgencyKey;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db.GrantAgency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrantAgencyRepository extends JpaRepository<GrantAgency, GrantAgencyKey> {
}
