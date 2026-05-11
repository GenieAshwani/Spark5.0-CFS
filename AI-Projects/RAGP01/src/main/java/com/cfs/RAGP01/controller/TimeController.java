package com.cfs.RAGP01.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tools")
public class TimeController {

    private final ChatClient chatClient;



    public TimeController(@Qualifier("timeChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;

    }

    @GetMapping("/chat")
    public ResponseEntity<String> webChat(@RequestParam String question)
    {
        String ans=chatClient.prompt()
                .user(question)
                .call()
                .content();
        return ResponseEntity.ok(ans);

    }

}
