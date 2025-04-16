package service;
import entity.KnowledgeBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.KnowledgeBaseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class KnowledgeBaseService {

    @Autowired
    private KnowledgeBaseRepository knowledgeBaseRepository;

    @Autowired
    private AuditLogService auditService;

    @Transactional
    public KnowledgeBase createKnowledgeBase(KnowledgeBase kb) {
        KnowledgeBase saved = knowledgeBaseRepository.save(kb);
        auditService.log("KnowledgeBase", "CREATE", "KnowledgeBase created with id: " + saved.getKnowledgeBaseId());
        return saved;
    }

    @Transactional
    public List<KnowledgeBase> createMultiple(List<KnowledgeBase> list) {
        List<KnowledgeBase> saved = knowledgeBaseRepository.saveAll(list);
        auditService.log("KnowledgeBase", "BATCH_CREATE", saved.size() + " knowledge bases created.");
        return saved;
    }

    @Transactional
    public KnowledgeBase updateKnowledgeBase(Long id, KnowledgeBase updatedKB) {
        return knowledgeBaseRepository.findById(id).map(kb -> {
            kb.setDirectory(updatedKB.getDirectory());
            KnowledgeBase saved = knowledgeBaseRepository.save(kb);
            auditService.log("KnowledgeBase", "UPDATE", "KnowledgeBase updated with id: " + saved.getKnowledgeBaseId());
            return saved;
        }).orElseThrow(() -> new RuntimeException("KnowledgeBase not found"));
    }

    @Transactional
    public void deleteKnowledgeBase(Long id) {
        knowledgeBaseRepository.deleteById(id);
        auditService.log("KnowledgeBase", "DELETE", "KnowledgeBase deleted with id: " + id);
    }

    public Optional<KnowledgeBase> findById(Long id) {
        return knowledgeBaseRepository.findById(id);
    }

    public List<KnowledgeBase> findAll() {
        return knowledgeBaseRepository.findAll();
    }
}