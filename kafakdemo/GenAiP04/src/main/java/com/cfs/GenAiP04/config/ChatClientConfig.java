package com.cfs.GenAiP04.config;


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
                        you are the helpful spring AI tutor
                        Answer clearly and keep response short
                        """)
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }
}
