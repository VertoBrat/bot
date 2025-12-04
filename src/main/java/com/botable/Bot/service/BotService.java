package com.botable.Bot.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BotService {

    @Setter
    @Getter
    private LocalDateTime lastCommentDate = LocalDateTime.now();
    @Setter
    @Getter
    private Long chartId;
    private final ChatGptService chatGptService;

    public Optional<String> handleIncomingText(String text) {
        return Optional.ofNullable(chatGptService.generate(text));
    }

}
