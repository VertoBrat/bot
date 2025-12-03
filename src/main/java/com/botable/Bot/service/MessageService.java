package com.botable.Bot.service;

import com.botable.Bot.domain.Message;
import com.botable.Bot.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public Optional<Message> findById(Long id) {
        return messageRepository.findById(id);
    }
}