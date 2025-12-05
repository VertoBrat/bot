package com.botable.Bot.telegram;

import com.botable.Bot.config.TelegramBotProperties;
import com.botable.Bot.service.BotService;
import com.botable.Bot.utils.BotUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class CrazyBot extends TelegramLongPollingBot {

    private final BotService botService;
    private final TelegramBotProperties properties;

    @Override
    public void onUpdateReceived(Update update) {
        String text = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        botService.setLastCommentDate(LocalDateTime.now());
        botService.setChartId(chatId);
        if (!update.hasMessage() || !update.getMessage().hasText() || !StringUtils.startsWith( text, "@" + properties.getUsername())) return;

        BotUtils.sleep(600);

        botService.handleIncomingText(text)
                .ifPresent(answer -> send(chatId, answer));
    }

    @Scheduled(fixedRate = 36000000L)
    private void sendScheduled() {
        LocalDateTime now = LocalDateTime.now();
        long diff = now.until(botService.getLastCommentDate(), ChronoUnit.MINUTES);
        if (diff < -60) {
            send(botService.getChartId(), "Мож попиздим, а?");
        }
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
