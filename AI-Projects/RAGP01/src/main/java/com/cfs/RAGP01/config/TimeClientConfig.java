package com.cfs.RAGP01.config;


import com.cfs.RAGP01.tools.TimeTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.generation.augmentation.ContextualQueryAugmenter;
import org.springframework.ai.rag.retrieval.search.DocumentRetriever;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.util.List;

@Configuration
public class TimeClientConfig {


    @Bean("timeChatClient")
    public ChatClient timeChatClient(ChatClient.Builder chatClientBuilder, TimeTools timeTools)
    {
        return chatClientBuilder
                .defaultTools(timeTools)
                .defaultAdvisors(List.of(
                        new SimpleLoggerAdvisor()
                ))
                .build();
    }


}
