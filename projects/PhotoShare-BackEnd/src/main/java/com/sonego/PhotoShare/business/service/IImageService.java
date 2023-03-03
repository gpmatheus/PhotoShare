package com.sonego.PhotoShare.business.service;

import com.sonego.PhotoShare.business.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface IImageService {

    public Image saveImage(Image image) throws IOException;

    public Image getImageById(UUID id) throws IOException;
}
