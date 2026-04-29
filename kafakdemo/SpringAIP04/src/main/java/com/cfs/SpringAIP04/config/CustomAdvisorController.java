package com.cfs.SpringAIP04.config;


import com.cfs.SpringAIP04.advisor.CustomAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/custom")
public class CustomAdvisorController {

    private final ChatClient chatClient;

    public CustomAdvisorController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    @GetMapping("/query")
    public CustomAdvisorResponse query(@RequestParam String msg)
    {
        ChatClientResponse res=chatClient.prompt()
                .user(msg)
                .advisors(new CustomAdvisor())
                .call()
                .chatClientResponse();

        return new CustomAdvisorResponse(res.chatResponse().getResult().getOutput().getText(),"Custom"

        ,res.context().get(CustomAdvisor.END_TIME_NANO));
    }

    public record CustomAdvisorResponse(String res,String advisorUsed,Object entTime)
    {

    }

}
