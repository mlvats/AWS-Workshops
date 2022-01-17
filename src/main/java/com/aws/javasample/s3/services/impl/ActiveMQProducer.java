package com.aws.javasample.s3.services.impl;

import com.google.gson.Gson;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.Map;

@Component
public class ActiveMQProducer {

    @Autowired
    ActiveMQConnectionFactory connectionFactory;

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendMessage(final String queueName, final String message) {

        //final String textMessage = "Hello " + message;
        //System.out.println("Sending message " + textMessage + "to queue - " + queueName);
        System.out.println("Sending message " + message + "to queue - " + queueName);
        jmsTemplate.send(queueName, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }

}
