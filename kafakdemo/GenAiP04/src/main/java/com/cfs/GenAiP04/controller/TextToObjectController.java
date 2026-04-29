package com.cfs.GenAiP04.controller;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class TextToObjectController {

    private final ChatClient chatClient;

    public TextToObjectController(ChatClient.Builder chatClientBuilder) {
        this.chatClient =chatClientBuilder
                .defaultAdvisors(new SimpleLoggerAdvisor()).build();
    }
    @GetMapping("/course")
    public CourseInfo extractCourseInfo(@RequestParam String msg)
    {
        return  chatClient
                .prompt()
                .user(msg)
                .options(OpenAiChatOptions.builder()
                        .temperature(0.4)
                        .maxTokens(1000)
                        .model("gpt-4o-mini")
                        .build())
                .call()
                .entity(CourseInfo.class);
    }


    public record CourseInfo(
            String title,
            String level,
            Integer durationInWeeks,
            Integer price,
            List<String> topics
    )
    {

    }

}
