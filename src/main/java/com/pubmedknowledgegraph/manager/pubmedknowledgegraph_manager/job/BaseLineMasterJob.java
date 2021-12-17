package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.job;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.repository.FileMetaDataRepository;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.repository.MetaDataRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.integration.partition.RemotePartitioningManagerStepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

@Configuration
public class BaseLineMasterJob {

    private final Logger logger = LogManager.getRootLogger();

    private final FileMetaDataRepository fileMetaDataRepository;
    private final MetaDataRepository metaDataRepository;

    private final JobBuilderFactory jobBuilderFactory;
    private final RemotePartitioningManagerStepBuilderFactory remotePartitioningManagerStepBuilderFactory;

    @Autowired
    public BaseLineMasterJob(FileMetaDataRepository fileMetaDataRepository, MetaDataRepository metaDataRepository, JobBuilderFactory jobBuilderFactory, RemotePartitioningManagerStepBuilderFactory remotePartitioningManagerStepBuilderFactory) {
        this.fileMetaDataRepository = fileMetaDataRepository;
        this.metaDataRepository = metaDataRepository;
        this.jobBuilderFactory = jobBuilderFactory;
        this.remotePartitioningManagerStepBuilderFactory = remotePartitioningManagerStepBuilderFactory;
    }

    @Bean
    public DirectChannel requests() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow outboundFlow(AmqpTemplate amqpTemplate) {
        return IntegrationFlows.from(requests())
                .handle(Amqp.outboundAdapter(amqpTemplate)
                        .routingKey("requests"))
                .get();
    }

    @Bean
    public DirectChannel replies() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow inboundFlow(ConnectionFactory connectionFactory) {
        return IntegrationFlows
                .from(Amqp.inboundAdapter(connectionFactory,"replies"))
                .channel(replies())
                .get();
    }

    @Bean(name = "managerBaseLinePartitioner")
    public Partitioner partitioner() {
        return new BaseLinePartitioner(metaDataRepository, fileMetaDataRepository);
    }

    @Bean(name = "managerBaseLineStep")
    public Step managerStep() {
        return this.remotePartitioningManagerStepBuilderFactory.get("managerBaseLineStep")
                .partitioner("workerBaseLineStep", partitioner())
                .outputChannel(requests())
                .inputChannel(replies())
                .build();
    }

    @Bean(name = "batchBaseLineJob")
    public Job remotePartitioningJob() {
        logger.info("Starting baseline job..");
        return this.jobBuilderFactory.get("remoteBaseLinePartitioningJob")
                .listener(jobExecutionListener())
                .incrementer(new RunIdIncrementer())
                .start(managerStep())
                .build();
    }

    @Bean(name = "managerBaseLineListener")
    public JobExecutionListener jobExecutionListener() {
        return new JobExecutionListener() {
            @Override
            public void beforeJob(JobExecution jobExecution) {

            }

            @Override
            public void afterJob(JobExecution jobExecution) {
                metaDataRepository.updateBaseLineCheckComputed("true");
            }
        };
    }

}
