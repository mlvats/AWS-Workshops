package com.aws.javasample.s3.services;

public interface ActiveMQServices {

    void sendMessage(String queueName, final String message);
}
