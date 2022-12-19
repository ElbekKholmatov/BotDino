package com.company;

import com.company.container.ComponentContainer;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class App {
    public static void main(String[] args) {

        try {

//            Database.loadData();

            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(ComponentContainer.MY_BOT);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


    }
}
