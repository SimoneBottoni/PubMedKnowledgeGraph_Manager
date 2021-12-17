package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.repository;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.metadata.AnnotationMetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnnotationMetaDataRepository extends JpaRepository<AnnotationMetaData, Long> {

    @Query("SELECT am.fileName FROM AnnotationMetaData am")
    List<String> getAll();

}
