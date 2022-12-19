package com.company.controller;


import com.company.AI.Translator;
import com.company.container.ComponentContainer;
import com.company.entity.Users;
import com.company.entity.Words;
import com.company.enums.GoogleEnum;
import com.company.enums.UserStatus;
import com.company.service.UserService;
import com.company.util.InlineKeyboardUtil;
import com.company.util.ReplyKeyboardConstants;
import com.company.util.ReplyKeyboardUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MainController {

    public static void handleMessage(Message message) {
        String chatId = String.valueOf(message.getChatId());

        if (message.hasText()) {
            handleText(message);
        } else if (message.hasContact()) {
            handleContact(message);
        } else if (message.hasLocation()) {
            handleLocation(message);
        } else if (message.hasPhoto()) {
            handlePhoto(message);
        } else if (message.hasDocument()) {
            handleDocument(message);
        }
    }

    private static void handleDocument(Message message) {
        String chatId = String.valueOf(message.getChatId());
        // ...
    }

    private static void handlePhoto(Message message) {
        String chatId = String.valueOf(message.getChatId());
        List<PhotoSize> photoSizeList = message.getPhoto();


    }

    private static void handleLocation(Message message) {
        String chatId = String.valueOf(message.getChatId());
        Location location = message.getLocation();


    }

    private static void handleContact(Message message) {
        String chatId = String.valueOf(message.getChatId());
        Contact contact = message.getContact();


    }



    private static void handleText(Message message) {
        UserService service = UserService.getInstance();


        String chatId = String.valueOf(message.getChatId());
        String text = message.getText();

        SendMessage sendMessage = new SendMessage();
        User user = message.getFrom();

        sendMessage.setChatId(chatId);
        if (text.equals("/start")) {


            sendMessage.setText("Welcome, " + user.getFirstName());
            Users users= Users.builder()
                    .chatId(String.valueOf(user.getId()))
                    .name(user.getFirstName())
                    .username(user.getUserName())
                    .isAutoAddWord(true)
                    .build();
            service.addUser(users);
            ComponentContainer.MY_BOT.sendMsg(sendMessage);

            sendMessage.setText("Tizim ishlash turini tanlang: ");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.getMenu());
        }else if (text.equals("/mywords")){
            List<Words> allMyWords = service.getAllMyWords(chatId);
            if (Objects.isNull(allMyWords)){
                sendMessage.setText("Sizda hozircha so'zlar yo'q");
            }else {
                StringBuilder sb=new StringBuilder();
                sb.append("Sizning so'zlaringiz: ");
                for (Words allMyWord : allMyWords) {
                    String translations="";
                    for (String s : allMyWord.getTranslation()) {
                        translations=translations+s+" | ";
                    }
                    sb.append(allMyWord.getWord()).append(": ").append(translations).append("\n");
                }
                sendMessage.setText(sb.toString());
            }

        }else if(text.equals("/config")){
            sendMessage.setText("Tizim ishlash turini tanlang: ");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.getMenu());
        } else if (text.equals(ReplyKeyboardConstants.ADD_MANUAL)) {

            ComponentContainer.USER_STATUS_MAP.put(chatId, UserStatus.ADD_WORD);

            sendMessage.setChatId(chatId);
            sendMessage.setText("Enter word: ");


        } else if (ComponentContainer.USER_STATUS_MAP.get(chatId) != null
                && ComponentContainer.USER_STATUS_MAP.get(chatId).equals(UserStatus.ADD_WORD)) {

            ComponentContainer.USER_STATUS_MAP.put(chatId, UserStatus.OPERATION);

            List<String> words=new ArrayList<>();
            ComponentContainer.USER_OBJECT_MAP.put(
                    chatId, new Words(null, chatId, text, words, null, false));

            sendMessage.setChatId(chatId);
            sendMessage.setReplyMarkup(InlineKeyboardUtil.getConfirmationWord());
            sendMessage.setText("Enter translation of the word: ");

        } else if (ComponentContainer.USER_STATUS_MAP.get(chatId) != null
                && ComponentContainer.USER_STATUS_MAP.get(chatId).equals(UserStatus.ADD_WORD_DEFINITION)) {

//            ComponentContainer.USER_STATUS_MAP.put(chatId,UserStatus.ADD_WORD_TRANSLATION);

            Words word = (Words) ComponentContainer.USER_OBJECT_MAP.get(chatId);

            word.setDefinition(text);

            sendMessage.setChatId(chatId);
            sendMessage.setText("Choose operation: ");

            ComponentContainer.USER_STATUS_MAP.put(chatId, UserStatus.OPERATION);

        } else if (ComponentContainer.USER_STATUS_MAP.get(chatId) != null
                && ComponentContainer.USER_STATUS_MAP.get(chatId).equals(UserStatus.ADD_WORD_TRANSLATION)) {

            Words word = (Words) ComponentContainer.USER_OBJECT_MAP.get(chatId);

            List<String> translation = word.getTranslation();
            translation.add(text);
            word.setTranslation(translation);

            sendMessage.setChatId(chatId);
            sendMessage.setText("Choose:  ");

            sendMessage.setReplyMarkup(InlineKeyboardUtil.getConfirmationWord());

        } else if (ComponentContainer.USER_STATUS_MAP.get(chatId) != null
                && ComponentContainer.USER_STATUS_MAP.get(chatId).equals(UserStatus.ADD_WORD_TRANSLATION)) {

            ComponentContainer.USER_STATUS_MAP.put(chatId, UserStatus.OPERATION);

            List<String> words=new ArrayList<>();
            ComponentContainer.USER_OBJECT_MAP.put(
                    chatId, new Words(null, chatId, text, words, null, false));

            sendMessage.setChatId(chatId);
            sendMessage.setReplyMarkup(InlineKeyboardUtil.getConfirmationWord());
            sendMessage.setText("Enter translation of the word: ");

        }
        else if(text.equals(ReplyKeyboardConstants.ADD_BY_GOOGLE)){
            ComponentContainer.USER_STATUS_MAP.put(chatId, UserStatus.ADD_WORD_WITH_GOOGLE);
            sendMessage.setChatId(chatId);
            sendMessage.setText("Enter word: ");
        } else if (ComponentContainer.USER_STATUS_MAP.get(chatId) != null
                && ComponentContainer.USER_STATUS_MAP.get(chatId).equals(UserStatus.ADD_WORD_WITH_GOOGLE)) {

            ComponentContainer.USER_STATUS_MAP.put(chatId, UserStatus.OPERATION);

            Words words1 = new Words();
            words1.setTranslation(Arrays.asList(Translator.translate(text)));
            words1.setWord(text);
            words1.setUserChatId(chatId);
            words1.setDeleted(false);
            List<String> words=new ArrayList<>();
            ComponentContainer.USER_OBJECT_MAP.put(
                    chatId,words1);

            sendMessage.setChatId(chatId);
            sendMessage.setReplyMarkup(InlineKeyboardUtil.getConfirmationWord());



        }

        ComponentContainer.MY_BOT.sendMsg(sendMessage);

    }



    public static void handleCallback(Message message, String data) {
        SendMessage sendMessage = new SendMessage();
        String chatId = String.valueOf(message.getChatId());

        if (ComponentContainer.USER_STATUS_MAP.get(chatId) != null
                && ComponentContainer.USER_STATUS_MAP.get(chatId).equals(UserStatus.OPERATION)) {
            sendMessage.setChatId(chatId);

            if (data.equals("_add_translation_")) {
                ComponentContainer.USER_STATUS_MAP.put(chatId, UserStatus.ADD_WORD_TRANSLATION);

                sendMessage.setText("Enter translation of the word: ");

            } else if (data.equals("_done_")) {
                UserService service = UserService.getInstance();
                Words word = (Words) ComponentContainer.USER_OBJECT_MAP.get(chatId);
                if (service.addWord(word)) {
                    sendMessage.setText("Successfully added");
                } else {
                    sendMessage.setText("Some error ❗");
                }
                ComponentContainer.USER_STATUS_MAP.put(chatId,UserStatus.ADD_WORD_DEFINITION);
            } else if (data.equals("_discard_")) {
                ComponentContainer.USER_STATUS_MAP.remove(chatId);
                ComponentContainer.USER_OBJECT_MAP.remove(chatId);
                sendMessage.setText("Rejected successfully");
            }
            ComponentContainer.MY_BOT.sendMsg(sendMessage);

        }

    }

}
