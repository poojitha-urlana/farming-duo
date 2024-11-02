package com.farming.system.Service;

import org.springframework.core.io.ByteArrayResource;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CropPredictionService {

    private final RestTemplate restTemplate;

    public CropPredictionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String predictDisease(MultipartFile imageFile) throws IOException {
        String flaskUrl = "http://localhost:5000/predict";

        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

       
        ByteArrayResource imageResource = new ByteArrayResource(imageFile.getBytes()) {
            @Override
            public String getFilename() {
                return imageFile.getOriginalFilename();  
            }
        };

        // Prepare the body of the request
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", imageResource);

        // Create the HTTP request entity
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // Send POST request to Flask API
        ResponseEntity<Map> response = restTemplate.postForEntity(flaskUrl, requestEntity, Map.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, String> result = response.getBody();
            return result.get("prediction");  // Get the prediction result from the response
        } else {
            throw new IOException("Error while calling Flask API: " + response.getStatusCode());
        }
    }
}
