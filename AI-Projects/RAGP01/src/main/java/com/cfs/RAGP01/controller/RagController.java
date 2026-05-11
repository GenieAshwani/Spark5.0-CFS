package com.cfs.RAGP01.controller;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rag")
public class RagController {

        private final ChatClient chatClient;



    public RagController(ChatClient chatClient) {
        this.chatClient = chatClient;

    }

    @Value("classpath:/promptTemplates/ragTemplate.st")
    Resource promptTemplate;

    @Value("classpath:/promptTemplates/systemTemplate.st")
    Resource systemTemplate;


    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam String question)
    {
        /*SearchRequest searchRequest= SearchRequest.builder()
                .query(question)
                .topK(5)
                .similarityThreshold(0.5)
                .build();

        List<Document> similarDocs = vectorStore.similaritySearch(searchRequest);
        String content=similarDocs.stream()
                .map(this::documentContent)
                .filter(t->t!=null && !t.isBlank())
                .collect(Collectors.joining(System.lineSeparator() +"----"+System.lineSeparator()));

        if(content.isBlank())
        {
            return ResponseEntity.ok("Sorry, I could not find relevant information");
        }*/

        String ans=chatClient.prompt()
             /*   .system(s->s.text(promptTemplate)
                        .param("documents",content))*/
                .user(question)
                .call()
                .content();
        return ResponseEntity.ok(ans);

    }

    @GetMapping("/chat/pdf")
    public ResponseEntity<String> chatpdf(@RequestParam String question)
    {
       /* SearchRequest searchRequest= SearchRequest.builder()
                .query(question)
                .topK(5)
                .similarityThreshold(0.5)
                .build();
*/
     /*   List<Document> similarDocs = vectorStore.similaritySearch(searchRequest);
        String content=similarDocs.stream()
                .map(this::documentContent)
                .filter(t->t!=null && !t.isBlank())
                .collect(Collectors.joining(System.lineSeparator() +"----"+System.lineSeparator()));

        if(content.isBlank())
        {
            return ResponseEntity.ok("Sorry, I could not find relevant information");
        }*/

        String ans=chatClient.prompt()
                /*.system(s->s.text(systemTemplate)
                        .param("documents",content))*/
                .user(question)
                .call()
                .content();
        return ResponseEntity.ok(ans);

    }


    @GetMapping("/chat/web")
    public ResponseEntity<String> webChat(@RequestParam String question)
    {


        String ans=chatClient.prompt()
                .user(question)
                .call()
                .content();
        return ResponseEntity.ok(ans);

    }



    private String documentContent(Document document)
    {
        String content=document.getFormattedContent();
        if(content==null || content.isBlank())
        {
            content=document.getText();
        }
        return content;
    }
}
