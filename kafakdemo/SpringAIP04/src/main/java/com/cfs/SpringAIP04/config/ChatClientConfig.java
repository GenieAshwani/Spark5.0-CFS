package com.cfs.SpringAIP04.config;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {


    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder)
    {
        return chatClientBuilder
                .defaultSystem("""
                        You are helpful Spring AI tutor
                        Answer in simple language with short example
                        """)
                //.defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }
}
