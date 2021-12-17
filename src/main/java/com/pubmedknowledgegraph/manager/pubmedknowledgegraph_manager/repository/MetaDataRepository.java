package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.repository;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.metadata.MetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MetaDataRepository extends JpaRepository<MetaData, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE MetaData m SET m.lastBaselineFileComputed =" +
            "CASE WHEN m.lastBaselineFileComputed < :file THEN :file ELSE m.lastBaselineFileComputed END")
    void updateLastBaselineFileComputed(@Param("file") String file);

    @Transactional
    @Modifying
    @Query("UPDATE MetaData m SET m.lastUpdateFileComputed =" +
            "CASE WHEN m.lastUpdateFileComputed < :file THEN :file ELSE m.lastUpdateFileComputed END")
    void updateLastUpdateFileComputed(@Param("file") String file);

    @Transactional
    @Modifying
    @Query("UPDATE MetaData m SET m.baseLineEnded = :check")
    void updateBaseLineCheckComputed(@Param("check") String check);

    @Transactional
    @Modifying
    @Query("UPDATE MetaData m SET m.lastBaselineFile =:file")
    void updateLastBaselineFile(@Param("file") String file);

    @Query("SELECT m FROM MetaData m")
    MetaData getMetaData();

    @Query("SELECT m.lastBaselineFileComputed FROM MetaData m")
    String getLastBaseLineFileComputed();

    @Query("SELECT m.lastUpdateFileComputed FROM MetaData m")
    String getLastUpdateFileComputed();

    @Query("SELECT m.lastBaselineFile FROM MetaData m")
    String getLastBaseLineFile();

    @Query("SELECT m.baseLineEnded FROM MetaData m")
    String getBaseLineEndedCheck();

    @Query("SELECT m.annotationEnabled FROM MetaData m")
    String getAnnotationEnabledCheck();

}
