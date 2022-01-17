package com.aws.javasample.s3.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class ActiveMQConfig {

/*    String BROKER_URL = "tcp://localhost:61616";
    String BROKER_USERNAME = "admin";
    String BROKER_PASSWORD = "admin";*/

    // Specify the connection parameters.
    private final static String BROKER_URL
            = "ssl://b-4dc567ff-5dd7-44dd-8861-2ce6a0b35e3f-1.mq.us-east-1.amazonaws.com:61617";
    private final static String BROKER_USERNAME = "moti";
    private final static String BROKER_PASSWORD = "zxcvbnmasdfg";

    //1. Create Connection Factory, we can create generic or Queue.
    @Bean
    public ActiveMQConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(BROKER_URL);
        connectionFactory.setUserName(BROKER_USERNAME);
        connectionFactory.setPassword(BROKER_PASSWORD);

        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        return template;
    }

    @Bean(name= "activemq")
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrency("1-1");
        return factory;
    }




}
