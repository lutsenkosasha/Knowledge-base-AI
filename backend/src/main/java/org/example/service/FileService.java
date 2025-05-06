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

    @Transactional
    public List<File> findAll(){
        return fileRepository.findAll();
    }

    @Transactional
    public void delete(Long id){
        fileRepository.deleteById(id);
    }

    @Transactional
    public File update(Long id, File updatedFile){
        return fileRepository.findById(id).map(file -> {
            file.setFileName(updatedFile.getFileName());
            auditLogService.log("File", "UPDATE", "File created with id: " + file.getFileId());
            return fileRepository.save(file);
        }).orElseThrow(() -> new RuntimeException("file not found"));
    }
}
