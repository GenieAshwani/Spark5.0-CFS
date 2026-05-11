package com.cfs.AskMeAnyThingAi.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    @PostMapping
    public Map<String ,String> chat(@RequestBody Map<String,String> body)
    {
        String question=body.get("question");
        String ans=chatClient.prompt()
                .user(question)
                .call()
                .content();
        return Map.of("answer",ans);
    }
}
