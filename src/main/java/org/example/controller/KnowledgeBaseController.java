package org.example.controller;

import org.example.entity.KnowledgeBase;
import org.example.service.KnowledgeBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/knowledge-base")
public class KnowledgeBaseController {

    @Autowired
    private KnowledgeBaseService knowledgeBaseService;

    @PostMapping
    public ResponseEntity<KnowledgeBase> createKnowledgeBase(@RequestBody KnowledgeBase kb) {
        return ResponseEntity.ok(knowledgeBaseService.createKnowledgeBase(kb));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<KnowledgeBase>> createBatch(@RequestBody List<KnowledgeBase> kbList) {
        return ResponseEntity.ok(knowledgeBaseService.createMultiple(kbList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<KnowledgeBase> getKnowledgeBase(@PathVariable Long id) {
        return knowledgeBaseService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<KnowledgeBase>> getAll() {
        return ResponseEntity.ok(knowledgeBaseService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<KnowledgeBase> updateKnowledgeBase(@PathVariable Long id, @RequestBody KnowledgeBase kb) {
        return ResponseEntity.ok(knowledgeBaseService.updateKnowledgeBase(id, kb));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKnowledgeBase(@PathVariable Long id) {
        knowledgeBaseService.deleteKnowledgeBase(id);
        return ResponseEntity.noContent().build();
    }
}