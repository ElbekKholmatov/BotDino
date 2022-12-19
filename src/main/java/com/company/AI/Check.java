package com.company.AI;

import com.company.domain.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Check {
    private static final String POSTS_API_URL = "https://api.dictionaryapi.dev/api/v2/entries/en/hello";

    public static void main(String[] args) throws IOException, InterruptedException {
//

        DefinitionsItem definitionsItem = new DefinitionsItem();
        License license = new License();
        MeaningsItem meaningsItem = new MeaningsItem();
        PhoneticsItem phoneticsItem = new PhoneticsItem();
        ResponseItem responseItem = new ResponseItem();

        Gson gson = new Gson();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(POSTS_API_URL))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String jsonString = gson.toJson(response.body());
        String expectedString = "";

        // parse JSON

        System.out.println(response.body());
//        request
//        response.body





    }
}
