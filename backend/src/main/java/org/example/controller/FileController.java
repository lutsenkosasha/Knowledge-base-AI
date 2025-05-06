package org.example.controller;

import org.example.dto.FileDTO;
import org.example.entity.File;
import jakarta.validation.Valid;
import org.example.service.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.example.service.FileService;

import java.util.List;

@RestController
@RequestMapping("api/files")
public class FileController {
    @Autowired
    private FileService fileService;
    @Autowired
    private DirectoryService directoryService;

    @GetMapping("/{directoryId}")
    public List<File> findAllFromDirectory(@PathVariable Long directoryId){
        return fileService.findAllFromDirectory(directoryId);
    }

    @GetMapping()
    public List<File> findAll(){
        return fileService.findAll();
    }

    @PostMapping()
    public ResponseEntity<File> addFile(@RequestBody @Valid FileDTO fileDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        File newFile = new File();
        newFile.setFileName(fileDTO.getFileName());
        newFile.setDirectory(directoryService.findById(Long.valueOf(fileDTO.getDirectoryId())).orElse(null));
        return ResponseEntity.ok(fileService.save(newFile));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        fileService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<File> update(@PathVariable Long id, @RequestBody File updatedFile){
        File updated = fileService.update(id, updatedFile);
        return ResponseEntity.ok(updated);
    }
}
