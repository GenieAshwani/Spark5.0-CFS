package com.cfs.File_service.repo;

import com.cfs.File_service.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileEntity,Long> {

    List<FileEntity> findByFolderId(Long folderId);
}
