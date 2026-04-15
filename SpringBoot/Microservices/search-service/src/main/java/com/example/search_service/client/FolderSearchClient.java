package com.example.search_service.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@FeignClient(name="folder-service",url = "http://localhost:8082")
public interface FolderSearchClient {

    @GetMapping("/api/folders")
    List<Map<String,Object>> getAllFolders();


    @GetMapping("/api/folders/parent/{parentId}")
    List<Map<String,Object>> getAllFilesByParent(@PathVariable Long parentId);

}
