package com.tunewalker.rest;

import com.tunewalker.rest.util.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.List;

@SpringBootApplication
public class TunewalkerRest {

	public static void main(String[] args) {
		SpringApplication.run(TunewalkerRest.class, args);

//		Region region = Region.US_EAST_2;
//		S3Client s3 = S3Client.builder()
//				.region(region)
//				.build();
//
//		ListObjectsRequest listObjects = ListObjectsRequest
//				.builder()
//				.bucket("podwalker-album-art")
//				.build();
//
//		ListObjectsResponse res = s3.listObjects(listObjects);
//		List<S3Object> objects = res.contents();
//		for (S3Object myValue : objects) {
//			System.out.print("\n The name of the key is " + myValue.key());
//		}

	}

}
