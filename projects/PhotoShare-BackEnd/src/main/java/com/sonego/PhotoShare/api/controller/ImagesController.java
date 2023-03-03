package com.sonego.PhotoShare.api.controller;

import com.sonego.PhotoShare.business.model.Image;
import com.sonego.PhotoShare.business.service.IImageFileService;
import com.sonego.PhotoShare.business.service.IImageService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/images")
@AllArgsConstructor
public class ImagesController {

    IImageService imageService;
    @GetMapping(path="/{id}")
    public ResponseEntity<byte[]> getImageBytes(@PathVariable UUID id) throws IOException {
        Image image = imageService.getImageById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(image.getContentType()))
                .body(image.getBytes());
    }
}
