package com.kardoaward.kardo.media.controller;


import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Collectors;

import com.kardoaward.kardo.media.service.MediaService;
import com.kardoaward.kardo.media.service.StorageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController("/media/storage")
@RequestMapping
@Slf4j
@AllArgsConstructor
@Validated
public class MediaUploadController {

    private final StorageService storageService;
    private final MediaService mediaService;

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                        path -> MvcUriComponentsBuilder.fromMethodName(MediaUploadController.class,
                                "serveFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/photo/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);

        if (file == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/video")
    public String handleFileUploadVideo(@RequestParam("video") MultipartFile video,
                                        RedirectAttributes redirectAttributes) {

        storageService.store(video);
        redirectAttributes.addFlashAttribute("message",
                "Вы успешно загрузили " + video.getOriginalFilename() + "!");

        return "redirect:/";
    }

    @PostMapping("/photo")
    public String handleFileUploadPhoto(@RequestParam("photo") MultipartFile photo,
                                        RedirectAttributes redirectAttributes) {

        storageService.store(photo);
        redirectAttributes.addFlashAttribute("message",
                " Вы успешно загрузили " + photo.getOriginalFilename() + "!");

        return "redirect:/";
    }

}

