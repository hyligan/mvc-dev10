package com.goit.dev.mvc.storage.controllers;

import com.goit.dev.mvc.storage.service.StorageService;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest")
public class StorageRestController {

    private StorageService storageService;

    public StorageRestController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/all")
    public List<Path> getAllFiles() {
        return storageService.loadAll().collect(Collectors.toList());
    }

    @GetMapping("/{filename}")
    public void getFile(@PathParam("filename") String filename) {

    }

    @DeleteMapping("/{filename}")
    public void deleteFile(@PathParam("filename") String filename) {
    }

    @PostMapping("/")
    public List<Path> createFile() {
        return storageService.loadAll().collect(Collectors.toList());
    }


    @PutMapping("/{filename}")
    public void updateFile(){
    }
}
