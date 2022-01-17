package com.aws.javasample.s3.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class SQSServicesImpl {

    private Logger logger = LoggerFactory.getLogger(SQSServicesImpl.class);

    @JmsListener(destination = "moti_sqs_queue",  containerFactory="sqs")
    public void receiveSQSMessages(String message) throws JMSException {
        logger.info("Received message {}", message);
        System.out.println("SQS Message : " + message);
    }

}
