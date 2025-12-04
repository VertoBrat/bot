package com.botable.Bot.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatGptService {

    private final ChatClient chatClient;

    public ChatGptService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String generate(String text) {
        return chatClient
                .prompt(text)
                .call()
                .content();
    }
}
