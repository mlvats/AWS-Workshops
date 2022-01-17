package com.aws.javasample.s3.config;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.destination.DynamicDestinationResolver;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;


import javax.jms.Session;

@Configuration
@EnableJms
public class SQSConfig {

    /*@Value("${aws.sqs.endpoint}")
    private String endpoint;

    @Value("${aws.access_key_id}")
    private String awsId;

    @Value("${aws.secret_access_key}")
    private String awsKey;

    @Value("${aws.s3.region}")
    private String region;*/

    private String awsId = "AKIATVG3AWL6UN5OMAGU";
    private String awsKey = "vCejPDW7tWE7fQnU9uZOraBcbnlgcCAcRo5/PSGJ";
    private String region = "us-east-1";

    AWSCredentials awsCredentials = new BasicAWSCredentials(awsId, awsKey);

    SQSConnectionFactory connectionFactory =
            SQSConnectionFactory.builder()
                    .withRegion(Region.getRegion(Regions.US_EAST_1))
                    .withAWSCredentialsProvider(new AWSStaticCredentialsProvider(awsCredentials))
                    .build();

    @Bean(name= "sqs")
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(this.connectionFactory);
        factory.setDestinationResolver(new DynamicDestinationResolver());
        factory.setConcurrency("3-10");
        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
        return factory;
    }

    @Bean
    public JmsTemplate defaultJmsTemplateSQS() {
        return new JmsTemplate(this.connectionFactory);
    }



}
