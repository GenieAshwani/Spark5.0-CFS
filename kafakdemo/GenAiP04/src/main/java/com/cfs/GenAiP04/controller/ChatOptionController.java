package com.cfs.GenAiP04.controller;

import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class ChatOptionController {

    private final ChatClient chatClient;

    public ChatOptionController(ChatClient chatClient) {
        this.chatClient =chatClient;
    }

    @GetMapping("/default")
    public String askWithDefaultOPtions(@RequestParam String query)
    {
        return  chatClient
                .prompt()
                .user(query)
                .call()
                .content();
    }

    @GetMapping("/ask")
    public ChatOptionResponse askWithCustomOptions(@RequestParam String query)
    {
        OpenAiChatOptions options = OpenAiChatOptions.builder()
                .model("gpt-4o-mini")
                .temperature(0.6)
                .maxTokens(100)
                .build();

        String ans = chatClient
                .prompt()
                .user(query)
                .options(options) // This should work if imports are correct
                .call()
                .content();

        return new ChatOptionResponse(query,ans, options.getModel(), options.getTemperature(), options.getMaxTokens());
    }


    public record ChatOptionResponse(
            String question,
            String ans,
            String model,
            Double temp,
            Integer maxTokens
    )
    {

    }

}
