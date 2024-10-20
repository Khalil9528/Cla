package com.classgen.classgen;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import com.classgen.classgen.service.LLMService;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClassGenIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    // Mock le service LLM
    @MockBean
    private LLMService llmService;

    @Test
    public void testGenerateClass_Success() {
        // Simule la réponse du LLMService
        Mockito.when(llmService.generateCode(anyString())).thenReturn("public class Car {}");

        // Envoie la requête avec le prompt
        String url = "/api/generateClass"; // le port est automatiquement injecté par @SpringBootTest

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestJson = "{\"prompt\":\"Create a class named Car\"}";

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        // Vérifie que la réponse contient la classe Car
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("public class Car");
    }
}
