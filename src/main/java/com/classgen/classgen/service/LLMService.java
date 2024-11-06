package com.classgen.classgen.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LLMService {

    private final String LLM_API_URL = "http://localhost:11434/api/generate";
    private final RestTemplate restTemplate;

    public LLMService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String generateCode(String prompt) {
        LLMRequest request = new LLMRequest("llama2", prompt);
        try {
            System.out.println("Sending request to LLM API: " + LLM_API_URL);

            // Capture the response as raw string
            String response = restTemplate.postForObject(LLM_API_URL, request, String.class);

            System.out.println("Received response from LLM API: " + response);

            // If response is in NDJSON format, process each line
            StringBuilder parsedResponse = new StringBuilder();
            if (response != null) {
                String[] lines = response.split("\n");  // Split by newlines (NDJSON format)
                for (String line : lines) {
                    // Assuming each line is a valid JSON object, process it (if needed)
                    parsedResponse.append(line).append("\n");
                    // Optionally parse the JSON if needed for further processing
                    // Example: ObjectMapper can be used to map the JSON to a Java object
                }
            }

            return parsedResponse.toString(); // Return processed response

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error during API call: " + e.getMessage());
            return "Error during code generation " + e;
        }
    }


}
