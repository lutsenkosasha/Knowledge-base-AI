package controller;

import entity.File;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.AuditLogService;
import service.FileService;

@RestController
@RequestMapping("api/files")
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addFile(@RequestBody @Valid File file, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        fileService.save(file);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
