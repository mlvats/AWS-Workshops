package com.aws.javasample.s3.services.impl;

import com.google.gson.Gson;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.Map;

@Component
public class ActiveMQListener {

    @JmsListener(destination = "inbound.queue",  containerFactory="activemq")
    @SendTo("outbound.queue")
    public String receiveMessage(final Message jsonMessage) throws JMSException {
        String messageData = null;
        System.out.println("Received message " + jsonMessage);
        String response = null;
        if(jsonMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage)jsonMessage;
            messageData = textMessage.getText();
            //Map map = new Gson().fromJson(messageData, Map.class);
            System.out.println("Received Text message " + messageData);
            response  = "Response back " + messageData;
        }
        return response;
    }


}
