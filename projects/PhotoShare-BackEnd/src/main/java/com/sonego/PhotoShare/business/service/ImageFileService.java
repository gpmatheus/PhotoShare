package com.sonego.PhotoShare.business.service;

import com.sonego.PhotoShare.business.model.Image;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class ImageFileService implements IImageFileService {

    private static final String IMAGES_PATH = "D:/photos/";

    @Override
    public String storeImageFile(Image image) throws IOException {
        String imageFileName = image.getId() + "." + image.getExtension();
        try (OutputStream os = new FileOutputStream(IMAGES_PATH + imageFileName)) {
            os.write(image.getBytes());
        }
        return imageFileName;
    }

    @Override
    public byte[] getImageBytesByFileName(String fileName) throws IOException {
        String imagePath = IMAGES_PATH + fileName;
        try (InputStream is = new FileInputStream(imagePath)) {
            return is.readAllBytes();
        }
    }
}
