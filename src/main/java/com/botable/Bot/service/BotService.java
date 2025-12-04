package com.botable.Bot.service;

import com.botable.Bot.domain.Message;
import com.botable.Bot.repository.TopicRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class BotService {

    @Setter
    @Getter
    private LocalDateTime lastCommentDate = LocalDateTime.now();
    @Setter
    @Getter
    private Long chartId;
    private final TopicRepository topicRepository;
    private final ChatGptService chatGptService;

    public Optional<String> handleIncomingText(String text) {
        return Optional.ofNullable(chatGptService.generate(text));


        /*return Optional.ofNullable(text)
                .map(String::toLowerCase)
                .flatMap(topicRepository::findMatchingTopic)
                .flatMap(topic -> Optional.ofNullable(topic.getMessages())
                        .flatMap(messages -> Optional.ofNullable(messages.get(new Random().nextInt(messages.size())))))
                .map(Message::getContent);*/
    }

}
