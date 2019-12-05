package com.example.s3;

import java.net.URL;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;

@Component
@PropertySource("classpath:app.properties")
public class S3FileUtil implements InitializingBean {

	@Value("${aws.s3.bucketName}")
	private String bucketName;

	@Value("${aws.sdk.accessKey}")
	private String accessKey;

	@Value("${aws.sdk.secretAccessKey}")
	private String secretAccessKey;

	@Value("${aws.sdk.region}")
	private String region;

	private BasicAWSCredentials awsCreds;
	private AmazonS3 s3Client;

	public String createSignedUrl(String fileName, HttpMethod method) {

		System.out.println(accessKey);

		// Set the presigned URL to expire after one minute.
		java.util.Date expiration = new java.util.Date();
		long expTimeMillis = expiration.getTime();
		expTimeMillis += 1000 * 60;
		expiration.setTime(expTimeMillis);

		// Generate the presigned URL.
		System.out.println("Generating pre-signed URL.");
		GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, fileName)
				.withMethod(method).withExpiration(expiration);
		URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);

		System.out.println("Pre-Signed URL: " + url.toString());
		return url.toString();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println(accessKey);
		awsCreds = new BasicAWSCredentials(accessKey, secretAccessKey);
		s3Client = AmazonS3ClientBuilder.standard().withRegion(region)
				.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
	}

}
