package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.repository;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db.AccessionNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessionNumberRepository extends JpaRepository<AccessionNumber, String> {
}
