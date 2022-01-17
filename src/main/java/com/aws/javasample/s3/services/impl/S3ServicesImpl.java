package com.aws.javasample.s3.services.impl;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;

import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.s3.S3Client;

import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.*;

@Service
public class S3ServicesImpl {

    private Logger logger = LoggerFactory.getLogger(S3ServicesImpl.class);

    @Autowired
    private AmazonS3 s3client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    //listing objects
    public ObjectListing listObjects() {
        return s3client.listObjects(bucketName);
    }

    //get an object
    //InputStream s3stream = s3Client.getObject("bucket", "key").getObjectContent();

    public S3Object getObject(String objectKey) {
        InputStream s3stream = s3client.getObject(bucketName, objectKey).getObjectContent();
        System.out.println(s3stream);
        return s3client.getObject(bucketName, objectKey);
    }

    public void getContentType (String keyName) {
        try {
            HeadObjectRequest objectRequest = HeadObjectRequest.builder()
                    .key(keyName)
                    .bucket(bucketName)
                    .build();

            HeadObjectResponse objectHead = S3Client.create().headObject(objectRequest);
            String type = objectHead.contentType();
            System.out.println("The object content type is " + type);

            InputStream s3stream = s3client.getObject(bucketName, keyName).getObjectContent();

            // Get an object and print its contents.
            System.out.println("Downloading an object");

            S3Object fullObject = s3client.getObject(new GetObjectRequest(bucketName, keyName));

            System.out.println("Content-Type: " + fullObject.getObjectMetadata().getContentType());
            System.out.println("Content: ");

            displayTextInputStream(s3stream);

        } catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            e.printStackTrace();
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Content: ");
        }
    }

    private static void displayTextInputStream(InputStream input) throws IOException {
        // Read the text input stream one line at a time and display each line.


        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println();
    }

}
