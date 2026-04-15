package com.example.search_service.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@FeignClient(
        name = "file-service",
        url = "http://localhost:8081",
        fallbackFactory = FileSearchClientFallbackFactory.class
)
public interface FileSearchClient {

    @GetMapping("/api/files")
    List<Map<String,Object>> getAllFiles();


    @GetMapping("/api/files/folder/{folderId}")
    List<Map<String,Object>> getAllFilesByFolderId(@PathVariable Long folderId);
}
