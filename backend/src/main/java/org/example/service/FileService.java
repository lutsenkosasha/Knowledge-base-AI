package org.example.service;

import org.example.entity.File;
import jakarta.transaction.Transactional;
import org.example.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private AuditLogService auditLogService;

    @Transactional
    public File save(File file){
        File savedFile = fileRepository.save(file);
        auditLogService.log("File", "CREATE", "File created with id: " + file.getFileId());
        return savedFile;
    }

    @Transactional
    public List<File> findAllFromDirectory(Long directoryId){
        return fileRepository.findAllByDirectory_DirectoryId(directoryId);
    }
}
