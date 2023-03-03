package com.sonego.PhotoShare.api.wrapper;

import com.sonego.PhotoShare.business.model.Image;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class ImageWrapper implements IImageWrapper {
    @Override
    public Image toImage(MultipartFile imageMultiPartFile) throws IOException {
        byte[] imageBytes = imageMultiPartFile.getBytes();
        String extension = imageMultiPartFile.getContentType().split("/")[1];
        Image image = new Image();
        image.setBytes(imageBytes);
        image.setExtension(extension);
        image.setContentType(imageMultiPartFile.getContentType());
        return image;
    }
}
