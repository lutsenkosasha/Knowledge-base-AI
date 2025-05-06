package org.example.service;

import org.example.entity.Directory;
import jakarta.transaction.Transactional;
import org.example.repository.DirectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectoryService {
    @Autowired
    private DirectoryRepository directoryRepository;
    @Autowired
    private AuditLogService auditLogService;

    @Transactional
    public List<Directory> findAll(){
        return directoryRepository.findAll();
    }
    @Transactional
    public List<Directory> findByDepartment(String department){
        return directoryRepository.findAllByDepartment(department);
    }
    @Transactional
    public Directory save(Directory directory){
        Directory saved = directoryRepository.save(directory);
        auditLogService.log("Directory", "CREATE", "Directory created with id: "
                + directory.getDirectoryId());
        return saved;
    }
    @Transactional
    public Optional<Directory> findById(Long directoryId) {
        return directoryRepository.findById(directoryId);
    }

    @Transactional
    public void delete(Long id){
        directoryRepository.deleteById(id);
    }
    @Transactional
    public Directory update(Long id, Directory updatedDirectory){
        return directoryRepository.findById(id).map(directory -> {
            directory.setDirectoryName(updatedDirectory.getDirectoryName());
            directory.setDepartment(updatedDirectory.getDepartment());
            auditLogService.log("Directory", "UPDATE", "Directory created with id: "
                    + directory.getDirectoryId());
            return directoryRepository.save(directory);
        }).orElseThrow(()-> new RuntimeException("Directory not found"));
    }
}
