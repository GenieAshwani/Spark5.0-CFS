package com.cfs.folder_service.controller;


import com.cfs.folder_service.model.FolderEntity;
import com.cfs.folder_service.repo.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/folders")
public class FolderController {


    @Autowired
    private FolderRepository folderRepository;


    @GetMapping
    public List<FolderEntity> getAllFolders()
    {
        return folderRepository.findAll();
    }

    @GetMapping("/{id}")
    public FolderEntity getFolder(@PathVariable Long id)
    {
        return folderRepository.findById(id).orElse(null);
    }

    @PostMapping
    public FolderEntity createFolder(@RequestBody FolderEntity folder)
    {
        return folderRepository.save(folder);

    }


    @DeleteMapping("/{id}")
    public void deleteFolder(@PathVariable Long id)
    {
        folderRepository.deleteById(id);
    }

    @GetMapping("/parent/{parentId}")
    public List<FolderEntity> getFoldersByParent(@PathVariable Long parentId)
    {
        return folderRepository.findByParentId(parentId);
    }

    @PostMapping("/create")
    public Map<String,Object> createNewFolder(@RequestBody Map<String,Object> request)
    {
        try {
            String name=(String) request.get("name");
            Long parentId=request.get("parentId")!=null ?
                    Long.valueOf(request.get("parentId").toString() ): null;

            FolderEntity newFolder=new FolderEntity();
            newFolder.setId(System.currentTimeMillis());
            newFolder.setName(name);
            newFolder.setParentId(parentId);

            FolderEntity saved=folderRepository.save(newFolder);
            return Map.of("Success",true,"folder",saved);
        }
        catch (Exception e)
        {
            return Map.of("Success",false,"error",e.getMessage());
        }
    }

}
