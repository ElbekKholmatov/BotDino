package com.company.util;

import com.company.entity.Words;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InlineKeyboardUtil {



    public static ReplyKeyboardMarkup getAllWordsButton(List<Words> words){






        return null;
    }

    public static ReplyKeyboard getSystemType() {
        InlineKeyboardMarkup markup=new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> genList=new ArrayList<>();

        InlineKeyboardButton byHand = makeButton("Qo'lda kiritish", "hand");
        InlineKeyboardButton auto = makeButton("Avto kiritish(Google translate)", "auto");

        List<InlineKeyboardButton> buttons=new ArrayList<>();
        buttons.add(byHand);
        buttons.add(auto);
        genList.add(buttons);
        markup.setKeyboard(genList);

        return markup;
    }


    public static InlineKeyboardButton makeButton(String text, String callback){
        Objects.requireNonNull(callback);
        Objects.requireNonNull(text);

        InlineKeyboardButton button=new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callback);
        return button;
    }

    public static InlineKeyboardMarkup getConfirmationWord() {
        InlineKeyboardButton button = makeButton("Done", "_done_");
        InlineKeyboardButton button2 = makeButton("Add translation", "_add_translation_");
        InlineKeyboardButton button3 = makeButton("Discard", "_discard_");

        List<List<InlineKeyboardButton>> genList=new ArrayList<>();

        List<InlineKeyboardButton> list=new ArrayList<>();
        list.add(button);
        list.add(button2);
        list.add(button3);

        genList.add(list);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(genList);
        return markup;
    }
    public static InlineKeyboardMarkup getConfirmationDefinition() {
        InlineKeyboardButton button = makeButton("Done", "_done_");
        InlineKeyboardButton button2 = makeButton("Add definition", "_add_definition_");
        InlineKeyboardButton button3 = makeButton("Discard", "_discard_");

        List<List<InlineKeyboardButton>> genList=new ArrayList<>();

        List<InlineKeyboardButton> list=new ArrayList<>();
        list.add(button);
        list.add(button2);
        list.add(button3);

        genList.add(list);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(genList);
        return markup;
    }
//    public static ReplyKeyboardMarkup get
}
