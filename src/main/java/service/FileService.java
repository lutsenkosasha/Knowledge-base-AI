package service;

import entity.File;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.FileRepository;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private AuditLogService auditLogService;

    @Transactional
    public void save(File file){
        fileRepository.save(file);
        auditLogService.log("File", "CREATE", "File created with id: " + file.getFile_id());
    }
}
