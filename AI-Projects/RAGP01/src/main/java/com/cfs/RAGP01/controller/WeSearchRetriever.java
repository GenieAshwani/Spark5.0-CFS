package com.cfs.RAGP01.controller;

import org.springframework.ai.document.Document;
import org.springframework.ai.rag.Query;
import org.springframework.ai.rag.retrieval.search.DocumentRetriever;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class WeSearchRetriever implements DocumentRetriever {
    private static final String SEARCH_URL="https://api.tavily.com/search";

    private final String apiKey;
    private final RestClient restClient;


    public WeSearchRetriever(@Value("${tavily.api-key}") String apiKey, RestClient restClient) {
        this.apiKey = apiKey;
        this.restClient = restClient;
    }

    @Override
    public List<Document> retrieve(Query query) {

        Map<?,?> response = restClient.post()
                .uri(SEARCH_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(h -> h.setBearerAuth(apiKey))
                .body(
                        Map.of(
                                "query", query.text(),
                                "search_depth", "basic",
                                "max_results", 5
                        )
                )
                .retrieve()
                .body(Map.class);


        return toDoc(response);
    }

    private List<Document> toDoc(Map<?,?> response) {

        List<Document> documentList=new ArrayList<>();

        if(response == null || !(response.get("results") instanceof List<?> results ))
        {
            return List.of();
        }

        for(Object res:results)
        {
            if(!(res instanceof Map<?,?> resultMap))
            {
                continue;
            }

            String title=text(resultMap.get("title"));
            String ulr=text(resultMap.get("url"));
            String content=text(resultMap.get("content"));

            if(content.isBlank())
            {
                continue;
            }

            documentList.add(new Document("""
                    Tavily Search result
                    Title: %s
                    URL: %s
                    Content: %s
                    
                    """.formatted(title, ulr, content),
                    Map.of("source","tavily","title",title,"url",ulr)
            ));


        }
        return documentList;
    }


    private String text(Object value)
    {
        return Objects.toString(value,"");
    }
}
