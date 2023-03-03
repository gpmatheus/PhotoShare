package com.sonego.PhotoShare.business.service;

import com.sonego.PhotoShare.business.exceptions.ExistingImageException;
import com.sonego.PhotoShare.business.exceptions.NotFoundException;
import com.sonego.PhotoShare.business.model.Image;
import com.sonego.PhotoShare.business.model.Post;
import com.sonego.PhotoShare.business.model.Profile;
import com.sonego.PhotoShare.persistence.repository.ImageRepository;
import com.sonego.PhotoShare.persistence.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.io.*;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ImageService implements IImageService {

    private ImageRepository imageRepository;

    private IImageFileService imageFileService;

    private String imagesURI;

    @Override
    public Image saveImage(Image image) throws IOException {
        byte[] imageBytes = image.getBytes();
        UUID imageId = UUID.nameUUIDFromBytes(imageBytes);
        imageRepository.findById(imageId).ifPresent(im -> {
            throw new ExistingImageException("This image already exists");
        });
        image.setId(imageId);
        image.setFileName(imageFileService.storeImageFile(image));
        image.setUrl(imagesURI + image.getId());
        return imageRepository.save(image);
    }

    @Override
    public Image getImageById(UUID id) throws IOException {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Image of id " + id + " could not be found"));
        image.setBytes(imageFileService.getImageBytesByFileName(image.getFileName()));
        return image;
    }

}