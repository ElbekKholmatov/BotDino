package com.company;

import com.company.container.ComponentContainer;

import com.company.threads.MyThread;
import com.company.threads.SendMessageThread;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class App {
    public static void main(String[] args) {
        SendMessageThread sendMessageThread=new SendMessageThread();
        sendMessageThread.run();


            Thread t1 = new MyThread("First Thread");

            t1.start();



        try {

            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(ComponentContainer.MY_BOT);


        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


    }
}
