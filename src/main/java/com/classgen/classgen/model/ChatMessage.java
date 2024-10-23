package com.classgen.classgen.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.persistence.PrePersist;
import jakarta.persistence.*;

@Entity
public class ChatMessage {

    @Id
    private UUID id;

    private String prompt;
    private String response;
    private LocalDateTime timestamp;

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }
        if (timestamp == null) {
            timestamp = LocalDateTime.now();
        }
    }

    public ChatMessage() {  }

    public ChatMessage(String prompt, String response, LocalDateTime timestamp) {
        this.prompt = prompt;
        this.response = response;
        this.timestamp = timestamp;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "id=" + id +
                ", prompt='" + prompt + '\'' +
                ", response='" + response + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}

