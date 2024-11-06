package com.classgen.classgen.service;

public class LLMRequest {
    private String model;
    private String prompt;

    // Constructor to initialize both model and prompt
    public LLMRequest(String model, String prompt) {
        this.model = model;
        this.prompt = prompt;
    }

    // Getter and setter for model
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    // Getter and setter for prompt
    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}
