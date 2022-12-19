package com.company.container;

import com.company.bot.MyBot;
import com.company.enums.AdminStatus;
import com.company.enums.UserStatus;

import java.util.HashMap;
import java.util.Map;

public interface ComponentContainer {
    String BOT_USERNAME = "DinoWordLearnBot";
    String BOT_TOKEN = "5864689997:AAHQw_3UmbljuQ0HnSEfy7VVn9kLs7YpIzU";
    String ADMIN_CHAT_ID = "616525392";
    MyBot MY_BOT = new MyBot();

    // chat id, admin status
    Map<String, AdminStatus> adminStatusMap = new HashMap<>();

    Map<String, UserStatus> USER_STATUS_MAP = new HashMap<>();

    // chat id, object
    Map<String, Object> USER_OBJECT_MAP = new HashMap<>();

}
