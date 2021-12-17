package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.repository;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.metadata.FailedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FailedFileRepository extends JpaRepository<FailedFile, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM FailedFile f WHERE f.fileName=:fileName")
    void deleteByFileName(@Param("fileName") String fileName);

    @Query("SELECT ff FROM FailedFile ff")
    List<FailedFile> getFailedFileList();

    @Query("SELECT ff.fileName FROM FailedFile ff WHERE ff.type='update'")
    List<String> getUpdateFailedFileNameList();

}
