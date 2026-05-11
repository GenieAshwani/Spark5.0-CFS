package com.cfs.ChatBot.controller;


import com.cfs.ChatBot.service.GeminiService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class GemeniWebSocketHandler extends TextWebSocketHandler {

    private final GeminiService geminiService;

    public GemeniWebSocketHandler(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        String userMessage=message.getPayload();
        if(userMessage==null || userMessage.isBlank())
        {
            session.sendMessage(new TextMessage("Please send message."));
            return;
        }

        try {
            String ans=geminiService.generateAnswer(userMessage);
            session.sendMessage(new TextMessage(ans));
        }
        catch (Exception e)
        {
            session.sendMessage(new TextMessage("Gemini request Failed: "+e.getMessage()));
        }
    }
}
