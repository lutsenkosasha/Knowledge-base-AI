package org.example.controller;

import org.example.entity.Directory;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.example.service.DirectoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/directories")
public class DirectoryController {
    @Autowired
    private DirectoryService directoryService;

    @GetMapping("/findAll")
    private List<Directory> findAll(){
        return directoryService.findAll();
    }

    @GetMapping("/{id}")
    private ResponseEntity<Directory> findById(@PathVariable Long id){
        return directoryService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/findByDepartment")
    private List<Directory> findDirectoriesOfDepartment(@RequestParam String department){
        return directoryService.findByDepartment(department);
    }

    @PostMapping()
    private ResponseEntity<Directory> create(@RequestBody @Valid Directory directory, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(directoryService.save(directory));
    }
}
