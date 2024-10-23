package com.classgen.classgen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import com.classgen.classgen.model.ChatMessageRepository;
import com.classgen.classgen.model.ChatMessage;
import java.util.UUID;


@Service
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;

    @Autowired
    public ChatService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    /**
     * Sauvegarde un nouveau message de chat dans la base de données.
     *
     * @param prompt   Le prompt envoyé au LLM.
     * @param response La réponse du LLM.
     * @return Le message de chat sauvegardé.
     */
    public ChatMessage saveChatMessage(String prompt, String response) {
        ChatMessage chatMessage = new ChatMessage(prompt, response, LocalDateTime.now());
        return chatMessageRepository.save(chatMessage);
    }

    /**
     * Récupère tous les messages de chat enregistrés dans la base de données.
     *
     * @return Liste des messages de chat.
     */
    public List<ChatMessage> getAllChatMessages() {
        return chatMessageRepository.findAll();
    }

    /**
     * Récupère un message de chat spécifique par son ID.
     *
     * @param id L'ID du message.
     * @return Le message de chat correspondant.
     */
    public ChatMessage getChatMessageById(UUID id) {
        return chatMessageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found with id: " + id));
    }

    /**
     * Supprime un message de chat par son ID.
     *
     * @param id L'ID du message à supprimer.
     */
    public void deleteChatMessageById(UUID id) {
        chatMessageRepository.deleteById(id);
    }
}
