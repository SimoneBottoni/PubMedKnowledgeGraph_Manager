package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.service;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.metadata.FailedFile;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.metadata.MetaData;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.repository.FailedFileRepository;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.repository.MetaDataRepository;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetaDataService {

    private final MetaDataRepository metaDataRepository;
    private final FailedFileRepository failedFileRepository;

    @Autowired
    public MetaDataService(MetaDataRepository metaDataRepository, FailedFileRepository failedFileRepository) {
        this.metaDataRepository = metaDataRepository;
        this.failedFileRepository = failedFileRepository;
    }

    public List<FailedFile> getFailedFileList() {
        return failedFileRepository.getFailedFileList();
    }

    public String failedFileToJson(List<FailedFile> failedFileList) {
        JsonMapper jsonMapper = new JsonMapper();
        ObjectNode objectNode = jsonMapper.createObjectNode();
        objectNode.putPOJO("FailedFile", failedFileList);
        return objectNode.toPrettyString();
    }

    public String getMetaData() {
        MetaData metaData = metaDataRepository.getMetaData();
        JsonMapper jsonMapper = new JsonMapper();
        ObjectNode objectNode = jsonMapper.createObjectNode();
        objectNode.put("lastBaselineFile", metaData.getLastBaselineFile());
        objectNode.put("lastBaselineFileComputed", metaData.getLastBaselineFileComputed());
        objectNode.put("lastUpdateFileComputed", metaData.getLastUpdateFileComputed());
        return objectNode.toPrettyString();
    }
}
