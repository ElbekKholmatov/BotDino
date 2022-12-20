package com.company.AI;

import com.company.entity.Definitions;
import com.company.entity.Meanings;
import com.company.entity.Word;
import com.company.entity.Words;
import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Check {
    private static final String POSTS_API_URL = "https://api.dictionaryapi.dev/api/v2/entries/en/";
//    public static String text = "live";
//    public static String chatId = "1231231231";
    public static Words dictionary(String text,String chatId){
//public static void main(String[] args) {

        try {
            HttpRequest postRequest = HttpRequest.newBuilder().uri(new URI(POSTS_API_URL+text)).GET().build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(postResponse.body());
            Gson gson = new Gson();
            String check = postResponse.body();
            if (check.contains("No Definitions Found"))
                return null;

            Word[] words = gson.fromJson(postResponse.body(), Word[].class);
            if(Objects.isNull(words))
                return null;
            ArrayList<String> definitionL = new ArrayList<>();
            ArrayList<String> synonymL = new ArrayList<>();
            for (Word word1 : words) {
                System.out.println("word = " + word1.getWord());
                for (Meanings meaning : word1.getMeanings()) {
                    for (Definitions definition : meaning.getDefinitions()) {
                        System.out.println("Example = " + definition.getExample());
                        if(definition.getExample() !=null)
                        definitionL.add(definition.getExample());
                    }
                    for (String synonym : meaning.getSynonyms()) {
                        System.out.println("synonym = " + synonym);
                        if(synonym !=null)
                            synonymL.add(synonym);
                    }
                }

                Words words1 = new Words(words[0].getWord(),chatId,new ArrayList<>(List.of(Translator.translate(text))),definitionL,synonymL,false);
                return words1;

            }

//            return words;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
