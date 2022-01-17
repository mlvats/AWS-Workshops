package com.aws.javasample.s3.services;

public interface SQSServices {
    void receiveSQSMessages(String message);
}
