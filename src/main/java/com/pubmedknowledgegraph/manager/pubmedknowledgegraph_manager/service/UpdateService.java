package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.service;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.repository.MetaDataRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class UpdateService {

    private final Logger logger = LogManager.getRootLogger();

    private final MetaDataRepository metaDataRepository;
    private final JobLauncher jobLauncher;
    private final Job job;

    @Autowired
    public UpdateService(MetaDataRepository metaDataRepository, JobLauncher jobLauncher, @Qualifier("batchUpdateJob") Job job) {
        this.metaDataRepository = metaDataRepository;
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @Scheduled(cron="${update.cron}")
    public void run() throws Exception {
        if (metaDataRepository.getBaseLineEndedCheck().equals("true")) {
            logger.info("Update started..");
            JobParameters params = new JobParametersBuilder()
                    .addString("batchUpdateJob", String.valueOf(System.currentTimeMillis()))
                    .toJobParameters();
            jobLauncher.run(job, params);
        } else {
            logger.info("Update can't start yet.");
        }
    }

}
