package com.aws.javasample.s3.services;

import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;

public interface S3Services  {
    ObjectListing listObjects(String bucketName);
    S3Object getObject(String objectKey);
    void getContentType (String keyName);
}
