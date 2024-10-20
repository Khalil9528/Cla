package com.classgen.classgen.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class LLMService {
    private final String LLM_API_URL = "https://api.example.com/generate";
    private final RestTemplate restTemplate;

    public LLMService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String generateCode(String prompt) {
        LLMRequest request = new LLMRequest(prompt);
        LLMResponse response = restTemplate.postForObject(LLM_API_URL, request, LLMResponse.class);
        return response != null ? response.getGeneratedCode() : null;
    }
}
