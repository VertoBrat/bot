package com.botable.Bot.telegram;

import com.botable.Bot.config.TelegramBotProperties;
import com.botable.Bot.service.BotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class CrazyBot extends TelegramLongPollingBot {

    private final BotService botService;
    private final TelegramBotProperties properties;

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) return;

        String text = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();

        botService.handleIncomingText(text)
                .ifPresent(answer -> send(chatId, answer));
    }

    private void send(Long chatId, String text) {
        try {
            execute(new SendMessage(chatId.toString(), text));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return properties.getUsername();
    }

    @Override
    public String getBotToken() {
        return properties.getToken();
    }
}
