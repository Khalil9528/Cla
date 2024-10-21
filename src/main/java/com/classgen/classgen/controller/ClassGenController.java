package com.classgen.classgen.controller;

import com.classgen.classgen.model.PromptRequest;
import com.classgen.classgen.service.LLMService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ClassGenController {

    private final LLMService llmService;

    public ClassGenController(LLMService llmService) {
        this.llmService = llmService;
    }

    @PostMapping("/generateClass")
    public ResponseEntity<String> generateClass(@RequestBody PromptRequest request) {
        String generatedClass = llmService.generateCode(request.getPrompt());
        return ResponseEntity.ok(generatedClass);
    }
}
