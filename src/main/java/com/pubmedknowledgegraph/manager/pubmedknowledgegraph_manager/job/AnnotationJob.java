package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.job;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.repository.AnnotationMetaDataRepository;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.repository.MetaDataRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.integration.partition.RemotePartitioningManagerStepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

@Configuration
public class AnnotationJob {

    private final Logger logger = LogManager.getRootLogger();

    private final AnnotationMetaDataRepository annotationMetaDataRepository;
    private final MetaDataRepository metaDataRepository;

    private final JobBuilderFactory jobBuilderFactory;
    private final RemotePartitioningManagerStepBuilderFactory remotePartitioningManagerStepBuilderFactory;

    @Autowired
    public AnnotationJob(AnnotationMetaDataRepository annotationMetaDataRepository, MetaDataRepository metaDataRepository, JobBuilderFactory jobBuilderFactory, RemotePartitioningManagerStepBuilderFactory remotePartitioningManagerStepBuilderFactory) {
        this.annotationMetaDataRepository = annotationMetaDataRepository;
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

    @Bean(name = "managerAnnotationBaseLinePartitioner")
    public Partitioner partitioner() {
        return new AnnotationPartitioner(metaDataRepository, annotationMetaDataRepository);
    }

    @Bean(name = "managerAnnotationBaseLineStep")
    public Step managerStep() {
        return this.remotePartitioningManagerStepBuilderFactory.get("managerAnnotationBaseLineStep")
                .partitioner("workerAnnotationBaseLineStep", partitioner())
                .outputChannel(requests())
                .inputChannel(replies())
                .build();
    }

    @Bean(name = "batchAnnotationBaseLineJob")
    public Job remotePartitioningJob() {
        logger.info("Starting annotation baseline job..");
        return this.jobBuilderFactory.get("remoteAnnotationBaseLinePartitioningJob")
                .incrementer(new RunIdIncrementer())
                .start(managerStep())
                .build();
    }

}
