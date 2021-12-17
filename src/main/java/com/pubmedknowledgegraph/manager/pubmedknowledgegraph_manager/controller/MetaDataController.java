package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.controller;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.metadata.FailedFile;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.service.MetaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/metadata")
public class MetaDataController {

    private final MetaDataService metaDataService;

    @Autowired
    public MetaDataController(MetaDataService metaDataService) {
        this.metaDataService = metaDataService;
    }

    @GetMapping
    public ResponseEntity<String> getMetadataStatus() {
        return ResponseEntity.status(HttpStatus.OK).body(metaDataService.getMetaData());
    }

    @GetMapping("/failedfile")
    public ResponseEntity<String> getFailedFile() {
        List<FailedFile> failedFileList = metaDataService.getFailedFileList();
        if (failedFileList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No failed file.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(metaDataService.failedFileToJson(failedFileList));
        }
    }
}
