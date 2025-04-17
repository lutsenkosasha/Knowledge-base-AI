package org.example.service;

import org.example.entity.Directory;
import jakarta.transaction.Transactional;
import org.example.repository.DirectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void save(Directory directory){
        directoryRepository.save(directory);
        auditLogService.log("Directory", "CREATE", "Directory created with id: "
                + directory.getDirectoryId());
    }
}
