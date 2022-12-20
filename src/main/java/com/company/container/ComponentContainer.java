package com.company.container;

import com.company.bot.MyBot;
import com.company.enums.AdminStatus;
import com.company.enums.UserStatus;

import java.util.HashMap;
import java.util.Map;

public interface ComponentContainer {
    String BOT_TOKEN = "5864689997:AAHQw_3UmbljuQ0HnSEfy7VVn9kLs7YpIzU";
    String BOT_USERNAME = "http://t.me/DinoWordLearnBot";

    MyBot MY_BOT = new MyBot();

     String USER_DB = "postgres";
     String PASSWORD_DB = "0";
     String URL_DB = "jdbc:postgresql://localhost:5432/learn_new_word";

    Map<String, UserStatus> USER_STATUS_MAP = new HashMap<>();

    // chat id, object
    Map<String, Object> USER_OBJECT_MAP = new HashMap<>();

}
