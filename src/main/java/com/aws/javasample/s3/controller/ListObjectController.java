package com.aws.javasample.s3.controller;

import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.aws.javasample.s3.services.impl.S3ServicesImpl;
import com.aws.javasample.s3.services.impl.ActiveMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.TextMessage;

@RestController
@RequestMapping(value = "/listObjects")
public class ListObjectController {

    @Value("${aws.s3.bucket}")
    private String bucketName;

    private Logger logger = LoggerFactory.getLogger(ListObjectController.class);

    @Autowired
    private S3ServicesImpl s3Services;

    @Autowired
    private ActiveMQProducer activeMQProducer;

    @RequestMapping(value = "/s3", method = RequestMethod.GET)
    public ObjectListing listObjects() {
        return s3Services.listObjects();
    }

    @RequestMapping(value = "/hello")
    public String home() {
        s3Services.getContentType("sample.xml");
        return "Hello World - v3!";
    }

    @RequestMapping(value = "/activemq")
    public String activemq() {

        String queueName = "inbound.queue";

        for (int x=0; x<1; x++) {
            String message = "Message " + x;
            System.out.println(message);
            activeMQProducer.sendMessage(queueName, message);
        }

        return "Hello World - v3!";
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.GET)
    public S3Object getObject(@PathVariable("key") String key) {
        return s3Services.getObject(key);
    }

}
