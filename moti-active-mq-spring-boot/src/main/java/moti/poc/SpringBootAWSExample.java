package moti.poc;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.jms.support.destination.DynamicDestinationResolver;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.jms.ConnectionFactory;

@SpringBootApplication
@EnableJms
@EnableScheduling
@Configuration
public class SpringBootAWSExample {

    @Bean
    @Primary
    public ConnectionFactory activeMqConnectionFactory( ActiveMQProperties properties) {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(properties.getBrokerUrl());
        connectionFactory.getPrefetchPolicy().setQueuePrefetch(1);
        RedeliveryPolicy redeliveryPolicy = connectionFactory.getRedeliveryPolicy();
        redeliveryPolicy.setUseExponentialBackOff(true);
        redeliveryPolicy.setMaximumRedeliveries(4);
        return connectionFactory;
    }

    @Bean
    public ConnectionFactory cachingConnectionFactory(
            @Qualifier("activeMqConnectionFactory")
                    ConnectionFactory activeMqConnectionFactory) {
        CachingConnectionFactory cachingConnectionFactory
                = new CachingConnectionFactory(activeMqConnectionFactory);
        cachingConnectionFactory.setCacheConsumers(true);
        cachingConnectionFactory.setCacheProducers(true);
        cachingConnectionFactory.setReconnectOnException(true);
        return cachingConnectionFactory;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAWSExample.class, args);

    }

    @Bean
    public MessageConverter jacksonMessageConverter() {
        MappingJackson2MessageConverter converter
                = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_messageType");
        return converter;
    }

    @Bean
    @Primary  //will make this as default template
    public JmsTemplate queueJmsTemplate(
            @Qualifier("cachingConnectionFactory")
                    ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setDestinationResolver(new DynamicDestinationResolver());
        jmsTemplate.setMessageConverter(jacksonMessageConverter());
       // jmsTemplate.setPriority(4);
   //     jmsTemplate.setTimeToLive(30000L);
    //    jmsTemplate.setExplicitQosEnabled(true);
        return jmsTemplate;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.activemq")
    public ActiveMQProperties activeMQProperties() {
        return new ActiveMQProperties();
    }



}
