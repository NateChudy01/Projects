package com.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class StringHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final String BUCKET_NAME = "istorage-project-marist"; // Updated bucket name
    AmazonS3 s3Client = AmazonS3ClientBuilder.standard().build();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
        String body = event.getBody();

        if (body == null || body.isEmpty()) {
            return createErrorResponse("Request body is empty.");
        }

        // Parse JSON body to extract Base64 image strings
        List<String> base64Images = extractBase64ImagesFromJson(body);

        if (base64Images == null || base64Images.isEmpty()) {
            return createErrorResponse("No images found in the request.");
        }

        System.out.println("Received " + base64Images.size() + " images.");

        // Store the list of uploaded file names
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseJson = mapper.createObjectNode();
        responseJson.put("status", "success");
        responseJson.put("message", "Images uploaded successfully!");
        responseJson.putArray("uploadedFiles");

        // Ensure the bucket exists
        ensureBucketExists();

        for (int i = 0; i < base64Images.size(); i++) {
            String base64Image = base64Images.get(i);
            byte[] imageBytes = decodeBase64Image(base64Image);

            if (imageBytes == null) {
                System.out.println("Invalid Base64 data for image at index " + i);
                continue;
            }

            // Generate a unique file name
            String fileName = generateUniqueFileName();
            uploadImageToS3(imageBytes, fileName);

            // Add the uploaded file name to the response JSON
            responseJson.withArray("uploadedFiles").add(fileName);
        }

        return createSuccessResponse(responseJson.toString());
    }

    private List<String> extractBase64ImagesFromJson(String body) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> jsonMap = objectMapper.readValue(body, Map.class);
    
            // Check for batch format: "images" key
            if (jsonMap.containsKey("images") && jsonMap.get("images") instanceof List) {
                return (List<String>) jsonMap.get("images");
            }
    
            // Check for single-image format: "image" key
            if (jsonMap.containsKey("image") && jsonMap.get("image") instanceof String) {
                return List.of((String) jsonMap.get("image"));
            }
        } catch (Exception e) {
            System.out.println("Error parsing JSON: " + e.getMessage());
        }
        return null;
    }
    

    private byte[] decodeBase64Image(String base64Image) {
        try {
            return Base64.getDecoder().decode(base64Image);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid Base64 data.");
            return null;
        }
    }

    private void uploadImageToS3(byte[] imageBytes, String fileName) {
        try {
            InputStream inputStream = new ByteArrayInputStream(imageBytes);
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, fileName, inputStream, null);
            s3Client.putObject(putObjectRequest);
            System.out.println("Image uploaded to S3 as " + fileName);
        } catch (Exception e) {
            System.out.println("Error uploading image to S3: " + e.getMessage());
        }
    }

    private String generateUniqueFileName() {
        //return "uploaded-image-" + Instant.now().toEpochMilli() + "-" + UUID.randomUUID() + ".jpg";
        int fileNumber = 1; // Start with file number 1
        String fileName;

        while (true) {
            fileName = "iStorage-Image-" + fileNumber + ".jpg";
            if (!s3Client.doesObjectExist(BUCKET_NAME, fileName)) {
                // If the file does not exist, break the loop
                break;
            }
            fileNumber++; // Increment the file number and try again
        }

        return fileName;
    }

    private APIGatewayProxyResponseEvent createErrorResponse(String message) {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setStatusCode(400); // Bad Request
        response.setBody(message);
        return response;
    }

    private APIGatewayProxyResponseEvent createSuccessResponse(String message) {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setStatusCode(200); // OK
        response.setBody(message);
        return response;
    }

    private void ensureBucketExists() {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
            .withRegion("us-east-2")
            .build();
        
        // Check if the bucket exists
        if (!s3Client.doesBucketExistV2(BUCKET_NAME)) {
            try {
                // Create the bucket if it doesn't exist
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(BUCKET_NAME);
                s3Client.createBucket(createBucketRequest);
                System.out.println("Bucket " + BUCKET_NAME + " created successfully.");
            } catch (Exception e) {
                System.out.println("Error creating bucket: " + e.getMessage());
            }
        } else {
            System.out.println("Bucket " + BUCKET_NAME + " already exists.");
        }
    }
}
