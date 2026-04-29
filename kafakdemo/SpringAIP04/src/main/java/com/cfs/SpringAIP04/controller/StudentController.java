package com.cfs.SpringAIP04.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class StudentController {

    private final ChatClient chatClient;

    public StudentController(ChatClient chatClient) {
        this.chatClient =chatClient;
    }


    @GetMapping("/query")
    public String courseQuery(@RequestParam String query)
    {
        return  chatClient
                .prompt()
                .user(query)
                .call()
                .content();
    }

}
