package com.classgen.classgen;


import org.junit.jupiter.api.Test;
import com.classgen.classgen.service.LLMService;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ClassGenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LLMService llmService;

    @Test
    public void testGenerateClass_Success() throws Exception {
        String prompt = "class Test {}";
        String generatedCode = "public class Test {}";

        Mockito.when(llmService.generateCode(prompt)).thenReturn(generatedCode);

        mockMvc.perform(post("/api/generateClass")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"prompt\":\"" + prompt + "\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(generatedCode));
    }

    @Test
    public void testGenerateClass_Failure() throws Exception {
        String prompt = "class Test {}";

        Mockito.when(llmService.generateCode(prompt)).thenReturn("");

        mockMvc.perform(post("/api/generateClass")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"prompt\":\"" + prompt + "\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Code generation failed."));
    }

    @Test
    public void testGenerateClass() {
        // Créer le prompt
        String prompt = "Create a Java class representing a Car with attributes make, model, and year";

        // Appeler le service pour générer la classe
        String generatedClass = llmService.generateCode(prompt);

        // Classe Java attendue
        String expectedClass = "public class Car { private String make; private String model; private int year; }";

        // Vérifier que la classe générée est correcte
        //assertEquals(expectedClass, generatedClass.trim());
    }


}
