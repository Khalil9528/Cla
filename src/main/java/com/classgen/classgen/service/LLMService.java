package com.classgen.classgen.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LLMService {

    private final String LLM_API_URL = "http://localhost:8080/generate"; // URL correcte ?
    private final RestTemplate restTemplate;

    public LLMService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String generateCode(String prompt) {
        LLMRequest request = new LLMRequest(prompt);
        try {
            System.out.println("Sending request to LLM API: " + LLM_API_URL);
            LLMResponse response = restTemplate.postForObject(LLM_API_URL, request, LLMResponse.class);
            System.out.println("Received response from LLM API: " + response);
            return response != null ? response.getGeneratedCode() : "Error during code generation";
        } catch (Exception e) {
            System.err.println("Error during API call: " + e.getMessage());
            return "Error during code generation" + e;
        }
    }

}
