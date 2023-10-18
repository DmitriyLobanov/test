package com.lobanov.bot.service;

import com.lobanov.bot.configuration.TelegramBotConfiguration;
import com.lobanov.bot.handlers.CallbackHandler;
import com.lobanov.bot.handlers.MessageHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;


@Slf4j
@Getter
@Setter
public class WriteReadBot extends SpringWebhookBot {

    String botPath;
    String botUsername;
    String botToken;
    private final TelegramBotConfiguration telegramBotConfiguration;
    private final MessageHandler messageHandler;


    public WriteReadBot(SetWebhook setWebhook,
                        MessageHandler messageHandler,
                        TelegramBotConfiguration telegramBotConfiguration) {

        super(setWebhook, telegramBotConfiguration.getBotToken());
        log.info("bot token {}", telegramBotConfiguration.getBotToken());
        this.messageHandler = messageHandler;
        this.telegramBotConfiguration = telegramBotConfiguration;

    }



    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return messageHandler.answerMessage(update.getMessage());
    }

    @Override
    public String getBotPath() {
        log.info("bot path {}", telegramBotConfiguration.getWebhookPath());
        return telegramBotConfiguration.getWebhookPath();
    }

    @Override
    public String getBotUsername() {
        log.info("bot getBotUsername {}", telegramBotConfiguration.getBotName());
        return telegramBotConfiguration.getBotName();
    }
}
