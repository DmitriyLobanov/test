package com.lobanov.bot.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@RequiredArgsConstructor
public class MessageHandler {

    public BotApiMethod<?> answerMessage(Message message) {
        String chatId = message.getChatId().toString();
        return new SendMessage(chatId, "ZXC");
    }
}
