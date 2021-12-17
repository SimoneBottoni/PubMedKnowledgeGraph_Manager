package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.job;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.repository.FailedFileRepository;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.repository.MetaDataRepository;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdatePartitioner implements Partitioner {

    private final Logger logger = LogManager.getRootLogger();

    private final MetaDataRepository metaDataRepository;
    private final FailedFileRepository failedFileRepository;

    private Util util;
    private List<String> pubMedFileList;

    public UpdatePartitioner(MetaDataRepository metaDataRepository, FailedFileRepository failedFileRepository) {
        this.metaDataRepository = metaDataRepository;
        this.failedFileRepository = failedFileRepository;
    }

    @Override
    public Map<String, ExecutionContext> partition(int i) {
        Map<String, ExecutionContext> partitions = new HashMap<>();

        if (metaDataRepository.getBaseLineEndedCheck().equals("true")) {

            util = new Util();
            pubMedFileList = new ArrayList<>();

            getUpdateFiles(getNFileToUpdate());

            for (String file : pubMedFileList) {
                if (!failedFileRepository.getUpdateFailedFileNameList().contains(file)) {
                    ExecutionContext context = new ExecutionContext();
                    context.put("fileName", file);
                    partitions.put("fileName " + file, context);
                }
                logger.info("Update: " + file);
            }
        }
        return partitions;
    }

    private int getNFileToUpdate() {
        int nFile;
        String lastUpdate = metaDataRepository.getLastUpdateFileComputed();
        if (lastUpdate.equals("")) {
            nFile = util.getNumberFromFileName(metaDataRepository.getLastBaseLineFile());
        } else {
            nFile = util.getNumberFromFileName(lastUpdate);
        }
        return nFile+1;
    }

    private void getUpdateFiles(int nFile) {
        while (true) {
            if (util.checkUpdateFileWithoutDownloadIt(nFile)) {
                pubMedFileList.add(util.getFileNameStringFromInt(nFile));
                nFile++;
            }
            else  {
                break;
            }
        }
    }
}
