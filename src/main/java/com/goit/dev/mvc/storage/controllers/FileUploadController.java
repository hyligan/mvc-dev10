package com.goit.dev.mvc.storage.controllers;

import com.goit.dev.mvc.storage.exceptions.StorageFileNotFoundException;
import com.goit.dev.mvc.storage.service.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import java.util.stream.Collectors;

@Controller
public class FileUploadController {

    private StorageService storageService;

    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) {
        model.addAttribute("files", storageService.loadAll().map(
                        path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                                "serveFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes,
                                   Model model) {

        storageService.store(file);
        model.addAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        return listUploadedFiles(model);
    }

    //SizeLimitExceededException

    @ExceptionHandler(SizeLimitExceededException.class)
    public ModelAndView handleStorageFileNotFound(SizeLimitExceededException exc) {
        ModelAndView result = new ModelAndView("uploadForm");
        result.addObject("message", exc.getMessage());
        result.addObject("files", storageService.loadAll().map(
                        path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                                "serveFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList()));
        return result;

    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ModelAndView handleStorageFileNotFound(StorageFileNotFoundException exc) {
        ModelAndView result = new ModelAndView("uploadForm");
        result.addObject("message", exc.getMessage());
        result.addObject("files", storageService.loadAll().map(
                        path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                                "serveFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList()));
        return result;
    }


}
