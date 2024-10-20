package com.classgen.classgen;

import com.classgen.classgen.service.LLMService;
import org.junit.jupiter.api.Test;
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
}
