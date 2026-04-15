package com.cfs.File_service.controller;


import com.cfs.File_service.model.FileEntity;
import com.cfs.File_service.repo.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileRepository fileRepository;

    private static final String UPLOAD_DIR="uploads";


    @GetMapping
    public List<FileEntity> getAllFiles(){
        return fileRepository.findAll();
    }

    @GetMapping("/{id}")
    public FileEntity getFile(@PathVariable Long id)
    {
        return fileRepository.findById(id).orElse(null);
    }

    @PostMapping
    public FileEntity createFile(@RequestBody FileEntity file)
    {
        return fileRepository.save(file);
    }

    @DeleteMapping("/{id}")
    public void deleteFile(@PathVariable Long id)
    {
        fileRepository.deleteById(id);
    }

    @GetMapping("/folder/{folderId}")
    public List<FileEntity> getFilesByFolder(@PathVariable Long folderId)
    {
        return fileRepository.findByFolderId(folderId);
    }

    @PostMapping("/upload")
    public Map<String,Object> uploadFile(@RequestParam("name") String name,
                                         @RequestParam("folderId") Long folderId,
                                         @RequestParam("file")MultipartFile file)
    {
        try{


            System.out.println("file name : "+ name);
            long fileSize=file.getSize();
            String fileName=file.getOriginalFilename();

            FileEntity newFile=new FileEntity();
            newFile.setId(System.currentTimeMillis());
            newFile.setName(fileName!=null ? fileName : name);
            newFile.setFolderId(folderId);
            newFile.setPath("/files/"+newFile.getId());


            //save file disk
            String uploadDirPath=UPLOAD_DIR+ File.separator+newFile.getId();
            Files.createDirectories(Paths.get(uploadDirPath));
            Path filePath = Paths.get(uploadDirPath,newFile.getName());
            Files.write(filePath,file.getBytes());

            System.out.println("dir path : "+ uploadDirPath);

            FileEntity saved=fileRepository.save(newFile);    //h2
            return Map.of("success","true","file",saved);

        } catch (Exception e) {
            return Map.of("Success",false,"error",e.getMessage());
        }
    }


    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable Long id)
    {
        try{
            FileEntity file=fileRepository.findById(id).orElse(null);
            if(file==null)
            {
                return ResponseEntity.noContent().build();
            }

            Path filePath=Paths.get(UPLOAD_DIR,id.toString(),file.getName());

            if(!Files.exists(filePath))
            {
                return ResponseEntity.noContent().build();
            }

            byte[] fileContent=Files.readAllBytes(filePath);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+file.getName() +"\"")
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                    .header(HttpHeaders.CONTENT_LENGTH,String.valueOf(fileContent.length))
                    .body(fileContent);
        } catch (IOException e)
        {
            return ResponseEntity.internalServerError().body("Error in downloading: "+e.getMessage());
        }
    }


}
