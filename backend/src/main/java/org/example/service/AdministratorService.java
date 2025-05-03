package org.example.service;
import org.example.entity.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.repository.AdministratorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private AuditLogService auditService;

    @Transactional
    public Administrator createAdministrator(Administrator admin) {
        Administrator saved = administratorRepository.save(admin);
        auditService.log("Administrator", "CREATE", "Administrator created with id: " + saved.getAdministratorId());
        return saved;
    }

    @Transactional
    public List<Administrator> createMultipleAdministrators(List<Administrator> admins) {
        List<Administrator> saved = administratorRepository.saveAll(admins);
        auditService.log("Administrator", "BATCH_CREATE", saved.size() + " administrators created.");
        return saved;
    }

    @Transactional
    public Administrator updateAdministrator(Long id, Administrator updatedAdmin) {
        return administratorRepository.findById(id).map(admin -> {
            admin.setEmail(updatedAdmin.getEmail());
            admin.setPassword(updatedAdmin.getPassword());
            admin.setUser(updatedAdmin.getUser());
            admin.setKnowledgeBase(updatedAdmin.getKnowledgeBase());
            Administrator saved = administratorRepository.save(admin);
            auditService.log("Administrator", "UPDATE", "Administrator updated with id: " + saved.getAdministratorId());
            return saved;
        }).orElseThrow(() -> new RuntimeException("Administrator not found"));
    }

    @Transactional
    public void deleteAdministrator(Long id) {
        administratorRepository.deleteById(id);
        auditService.log("Administrator", "DELETE", "Administrator deleted with id: " + id);
    }

    public Optional<Administrator> findById(Long id) {
        return administratorRepository.findById(id);
    }

    public List<Administrator> findAll() {
        return administratorRepository.findAll();
    }
}