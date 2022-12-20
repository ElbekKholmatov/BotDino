package com.company.AI;

import com.company.domain.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Check {
    private static final String POSTS_API_URL = "https://api.dictionaryapi.dev/api/v2/entries/en/hello";
    public static void main(String[] args) {
        try {
            HttpRequest postRequest = HttpRequest.newBuilder().uri(new URI(POSTS_API_URL)).GET().build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(postResponse.body());
            Gson gson = new Gson();
            Word[] words = gson.fromJson(postResponse.body(), Word[].class);
            for (Word word1 : words) {
                System.out.println("word = " + word1.getWord());
                for (Meanings meaning : word1.getMeanings()) {
                    for (Definitions definition : meaning.getDefinitions()) {
                        System.out.println("Example = " + definition.getExample());
                    }
                    for (String synonym : meaning.getSynonyms()) {
                        System.out.println("synonym = " + synonym);
                    }
                }
            }
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
