package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.job;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.repository.AnnotationMetaDataRepository;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.repository.MetaDataRepository;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnnotationPartitioner implements Partitioner {

    private final Logger logger = LogManager.getRootLogger();

    private final MetaDataRepository metaDataRepository;
    private final AnnotationMetaDataRepository annotationMetaDataRepository;

    public AnnotationPartitioner(MetaDataRepository metaDataRepository, AnnotationMetaDataRepository annotationMetaDataRepository) {
        this.metaDataRepository = metaDataRepository;
        this.annotationMetaDataRepository = annotationMetaDataRepository;
    }

    @Override
    public Map<String, ExecutionContext> partition(int i) {
        Map<String, ExecutionContext> partitions = new HashMap<>();
        Util util = new Util();
        List<String> alreadyComputedFiles = getAnnotationBaselineStatus();

        for (int num = 1; num <= util.getNumberFromFileName(getLastBaselinefile()); num++) {
            String fileName = util.getFileNameStringFromInt(num);
            if (!alreadyComputedFiles.contains(fileName)) {
                ExecutionContext context = new ExecutionContext();
                context.put("fileName", fileName);
                partitions.put("fileName " + fileName, context);
            }
            logger.info("Annotating BaseLine File: " + fileName);
        }
        return partitions;
    }

    private List<String> getAnnotationBaselineStatus() {
        return annotationMetaDataRepository.getAll();
    }

    private String getLastBaselinefile() {
        return metaDataRepository.getLastBaseLineFile();
    }
}
