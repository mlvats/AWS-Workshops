package moti.poc;

import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;

public class JmsAWSSQSConsumer {

    public static void main(String[] args) throws  Exception {

        // 1. Create the SQS Connection Factory

        SQSConnectionFactory connectionFactory = SQSConnectionFactory.builder()
                .withRegion(Region.getRegion(Regions.US_EAST_1))
                .withAWSCredentialsProvider(new DefaultAWSCredentialsProviderChain())
                .build();





    }

}
