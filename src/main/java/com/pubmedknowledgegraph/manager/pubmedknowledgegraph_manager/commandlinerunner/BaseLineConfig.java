package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.commandlinerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class BaseLineConfig implements CommandLineRunner {

    private final Logger logger = LogManager.getRootLogger();

    private final JobLauncher jobLauncher;
    private final Job job;

    @Autowired
    public BaseLineConfig(JobLauncher jobLauncher, @Qualifier("batchBaseLineJob") Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Init BaseLine.");
        JobParameters params = new JobParametersBuilder()
                .addString("batchBaseLineJob", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(job, params);
    }

}
