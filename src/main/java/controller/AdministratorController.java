package controller;

import entity.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.AdministratorService;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @PostMapping
    public ResponseEntity<Administrator> createAdministrator(@RequestBody Administrator admin) {
        return ResponseEntity.ok(administratorService.createAdministrator(admin));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Administrator>> createAdministrators(@RequestBody List<Administrator> admins) {
        return ResponseEntity.ok(administratorService.createMultipleAdministrators(admins));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrator> getAdministrator(@PathVariable Long id) {
        return administratorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Administrator>> getAllAdministrators() {
        return ResponseEntity.ok(administratorService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrator> updateAdministrator(@PathVariable Long id, @RequestBody Administrator admin) {
        return ResponseEntity.ok(administratorService.updateAdministrator(id, admin));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministrator(@PathVariable Long id) {
        administratorService.deleteAdministrator(id);
        return ResponseEntity.noContent().build();
    }
}