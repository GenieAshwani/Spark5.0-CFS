package com.cfs.RAGP01.rag;


import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfsLoader {

    private final VectorStore vectorStore;

    @Value("classpath:cfs-rag.pdf")
    Resource policyFile;

    @Value("${spring.ai.openai.api-key:}")
    String openAiApiKey;


    public CfsLoader(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    public void loadData()
    {
        if (openAiApiKey == null || openAiApiKey.isBlank() || openAiApiKey.startsWith("${")) {
            System.out.println("Skipping PDF indexing because OPENAI_API_KEY / OPEN_API_KEY is not configured.");
            return;
        }

        TikaDocumentReader tikaDocumentReader=new TikaDocumentReader(policyFile);
        List<Document> documents = tikaDocumentReader.get();
        List<Document> chunks= TokenTextSplitter.builder()
                .withChunkSize(300)
                .withMinChunkSizeChars(80)
                .withMaxNumChunks(20)
                .build()
                .split(documents);
        vectorStore.add(chunks);

    }
}
