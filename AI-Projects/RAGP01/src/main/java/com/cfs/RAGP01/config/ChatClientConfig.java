package com.cfs.RAGP01.config;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.generation.augmentation.ContextualQueryAugmenter;
import org.springframework.ai.rag.retrieval.search.DocumentRetriever;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.client.RestClient;

import java.util.List;

@Configuration
public class ChatClientConfig {


    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder,RetrievalAugmentationAdvisor retrievalAugmentationAdvisor)
    {
        return chatClientBuilder
                .defaultAdvisors(List.of(
                        new SimpleLoggerAdvisor(),retrievalAugmentationAdvisor
                ))
                .build();
    }

/*
    @Bean
    DocumentRetriever documentRetriever(VectorStore vectorStore) {
        return VectorStoreDocumentRetriever.builder()
                .vectorStore(vectorStore)
                .similarityThreshold(0.5)
                .topK(5)
                .build();
    }*/


 /*   @Bean
    RetrievalAugmentationAdvisor retrievalAugmentationAdvisor(
            DocumentRetriever documentRetriever,
           @Value("classpath:/promptTemplates/systemTemplate.st") Resource promptTemplate
    )
    {
        PromptTemplate promptTemplate1=new PromptTemplate(promptTemplate);
        return RetrievalAugmentationAdvisor.builder()
                .documentRetriever(documentRetriever)
                .queryAugmenter(ContextualQueryAugmenter.builder()
                        .promptTemplate(promptTemplate1)
                        .allowEmptyContext(false)
                        .build())
                .build();
    }
*/

    @Bean
    RetrievalAugmentationAdvisor retrievalAugmentationAdvisor(DocumentRetriever documentRetrieve)
    {

        return RetrievalAugmentationAdvisor.builder()
                .documentRetriever(documentRetrieve)
                .queryAugmenter(ContextualQueryAugmenter.builder()
                        .allowEmptyContext(false)
                        .build())
                .build();
    }

    @Bean
    public RestClient getInstance()
    {
        return RestClient.builder().build();
    }

}
