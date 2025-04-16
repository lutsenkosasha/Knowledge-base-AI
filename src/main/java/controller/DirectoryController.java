package controller;

import entity.Directory;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.DirectoryService;

import java.util.List;

@RestController
@RequestMapping("api/directories")
public class DirectoryController {
    @Autowired
    private DirectoryService directoryService;

    @GetMapping("/findAll")
    private List<Directory> findAll(){
        return directoryService.findAll();
    }

    @GetMapping("/findByDepartment")
    private List<Directory> findDirectoriesOfDepartment(@RequestParam String department){
        return directoryService.findByDepartment(department);
    }

    @PostMapping("/add")
    private ResponseEntity<HttpStatus> create(@RequestBody @Valid Directory directory, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        directoryService.save(directory);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
