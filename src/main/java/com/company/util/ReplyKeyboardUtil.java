package com.company.util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Arrays;
import java.util.List;

public class ReplyKeyboardUtil {
    public static ReplyKeyboard getMenu() {

        KeyboardRow row = getRows(
                getButtons(ReplyKeyboardConstants.ADD_MANUAL),
                getButtons(ReplyKeyboardConstants.ADD_BY_GOOGLE));
        KeyboardRow row1=getRows(
                getButtons(ReplyKeyboardConstants.SEARCH)
        );
        ReplyKeyboardMarkup keyboardMarkup=new ReplyKeyboardMarkup(List.of(row,row1));
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setSelective(true);
        return keyboardMarkup;
    }

    private static KeyboardRow getRows(KeyboardButton ... buttons) {
        return new KeyboardRow(Arrays.asList(buttons));
    }

    private static KeyboardButton getButtons(String text) {
        return new KeyboardButton(text);
    }
}
