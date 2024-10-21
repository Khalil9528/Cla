package com.classgen.classgen.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import java.util.HashMap;

@Service
public class LLMService {

    private final RestTemplate restTemplate;

    @Value("${ollama.api.url}")
    private String ollamaApiUrl;

    public LLMService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String generateCode(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "llama2"); // Spécifie ici le modèle Ollama
        requestBody.put("prompt", prompt);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        String response = restTemplate.postForObject(ollamaApiUrl, entity, String.class);
        return response != null ? response : "Erreur lors de la génération du code.";
    }
}
