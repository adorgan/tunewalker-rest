package com.tunewalker.rest.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class S3Util {
    private static final String BUCKET = "tunewalker-photos";
    public static String S3_URL = "https://tunewalker-photos.s3.us-east-2.amazonaws.com/";

    public static void uploadFile(String fileName, InputStream inputStream)
            throws AwsServiceException, SdkClientException, IOException {
        Region region = Region.US_EAST_2;
        S3Client s3Client = S3Client.builder()
                .region(region)
                .build();



        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(BUCKET)
                .key(fileName)
                .acl("public-read")
                .build();

        s3Client.putObject(request,
                RequestBody.fromInputStream(inputStream, inputStream.available()));
    }
}