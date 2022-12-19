package com.company.AI;

import com.darkprograms.speech.translator.GoogleTranslate;

import java.io.IOException;

public class Translator {
    public static String translate(String text){

        try {

            String translate = GoogleTranslate.translate("en", text);
            return translate;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "No available translation";
    }
}
