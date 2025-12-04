package com.botable.Bot.service;

import com.botable.Bot.domain.Message;
import com.botable.Bot.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class BotService {

    private final TopicRepository topicRepository;

    public Optional<String> handleIncomingText(String text) {
        return Optional.ofNullable(text)
                .map(String::toLowerCase)
                .flatMap(topicRepository::findMatchingTopic)
                .flatMap(topic -> Optional.ofNullable(topic.getMessages())
                        .flatMap(messages -> Optional.ofNullable(messages.get(new Random().nextInt(messages.size())))))
                .map(Message::getContent);
    }
}
