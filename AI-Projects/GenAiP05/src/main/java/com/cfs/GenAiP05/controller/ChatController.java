package com.cfs.GenAiP05.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class ChatController {

    private final ChatClient chatClient;

    private final ChatMemory inMemoryChatMemory;

    private final ChatMemory jdbcChatMemory;

    public ChatController(ChatClient chatClient,@Qualifier("inMemoryChatMemory") ChatMemory inMemoryChatMemory, @Qualifier("jdbcChatMemory") ChatMemory jdbcChatMemory) {
        this.chatClient =chatClient;
        this.inMemoryChatMemory = inMemoryChatMemory;
        this.jdbcChatMemory = jdbcChatMemory;
    }


    @GetMapping("/chat")
    public String courseQuery(@RequestParam String query)
    {
        return  chatClient
                .prompt()
                .user(query)
                .call()
                .content();
    }


    @GetMapping("/chat/in-memory")
    public String chatInMemory(@RequestParam String query)
    {
         String response=chatClient.prompt()
                 .user(query)
                 .advisors(MessageChatMemoryAdvisor.builder(inMemoryChatMemory)
                         .conversationId("1234")
                         .build())
                 .call()
                 .content();

         return response;
    }


    @GetMapping("/chat/jdbc")
    public String chatJdbcMemory(@RequestParam String query)
    {
        String response=chatClient.prompt()
                .user(query)
                .advisors(MessageChatMemoryAdvisor.builder(jdbcChatMemory)
                        .conversationId("1234")
                        .build())
                .call()
                .content();

        return response;
    }

}
