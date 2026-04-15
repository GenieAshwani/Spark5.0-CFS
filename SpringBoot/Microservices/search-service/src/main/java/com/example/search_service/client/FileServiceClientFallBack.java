package com.example.search_service.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class FileServiceClientFallBack implements FileSearchClient{
    private static final Logger log = LoggerFactory.getLogger(FileServiceClientFallBack.class);
    private final Throwable cause;

    public FileServiceClientFallBack() {
        this(null);
    }

    public FileServiceClientFallBack(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public List<Map<String, Object>> getAllFiles() {
        log.warn("Falling back for getAllFiles because file-service is unavailable", cause);
        return List.of();
    }

    @Override
    public List<Map<String, Object>> getAllFilesByFolderId(Long folderId) {
        log.warn("Falling back for getAllFilesByFolderId({}) because file-service is unavailable", folderId, cause);
        return List.of();
    }
}
