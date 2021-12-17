package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.repository;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db.Chemical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChemicalRepository extends JpaRepository<Chemical, String> {
}
