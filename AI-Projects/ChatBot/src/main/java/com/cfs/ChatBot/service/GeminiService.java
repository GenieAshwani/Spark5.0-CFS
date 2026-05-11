package com.cfs.ChatBot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;
import tools.jackson.databind.JsonNode;

import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

    private final RestClient restClient;
    private final String apiKey;
    private final String model;


    public GeminiService(RestClient restClient,
                         @Value("${gemini.api.key}") String apiKey, @Value("${gemini.model}") String model) {
        this.restClient =restClient;
        this.apiKey = apiKey;
        this.model = model;
    }

    public String generateAnswer(String prompt)
    {
        if(apiKey == null || apiKey.isBlank() || model.isBlank())
        {
            return "Gemini API key is missing.";
        }
        System.out.println("api key "+apiKey);
        System.out.println("model "+model);

        String ulr= UriComponentsBuilder
                .fromUriString("https://generativelanguage.googleapis.com/v1beta/models/{model}:generateContent")
                .queryParam("key",apiKey)
                .buildAndExpand(model)
                .toUriString();

        Map<String,Object> reqBody= Map.of(
                "contents", List.of(
                    Map.of(
                            "role","user",
                            "parts",List.of(Map.of("text",prompt))
                    )
                )
        );

        JsonNode res=restClient.post()
                .uri(ulr)
                .contentType(MediaType.APPLICATION_JSON)
                .body(reqBody)
                .retrieve()
                .body(JsonNode.class);
        return extactText(res);

    }

    private String extactText(JsonNode response)
    {
        JsonNode parts=response
                .path("candidates")
                .path(0)
                .path("content")
                .path("parts");

        StringBuffer ans=new StringBuffer();
        for(JsonNode part:parts)
        {
            String text=part.path("text").asText("");
            if(!text.isBlank())
            {
                ans.append(text);
            }
        }
        return ans.toString();
    }

}
