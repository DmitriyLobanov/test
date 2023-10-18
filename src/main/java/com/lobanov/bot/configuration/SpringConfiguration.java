package com.lobanov.bot.configuration;

import com.lobanov.bot.handlers.MessageHandler;
import com.lobanov.bot.service.WriteReadBot;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@Configuration
@RequiredArgsConstructor
public class SpringConfiguration {

    private final TelegramBotConfiguration telegramBotConfiguration;

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder()
                .url(telegramBotConfiguration.getWebhookPath()).build();
    }

    @Bean
    public WriteReadBot springWebhookBot(SetWebhook setWebhook,
                                  MessageHandler messageHandler) {
        WriteReadBot bot = new WriteReadBot(setWebhook, messageHandler, telegramBotConfiguration);

        bot.setBotPath(telegramBotConfiguration.getWebhookPath());
        bot.setBotUsername(telegramBotConfiguration.getBotName());
        bot.setBotToken(telegramBotConfiguration.getBotToken());

        return bot;

    }
}
