package com.sonego.PhotoShare.business.service;

import com.sonego.PhotoShare.business.model.Image;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

public interface IImageFileService {

    public String storeImageFile(Image image) throws IOException;
    public byte[] getImageBytesByFileName(String fileName) throws IOException;

}
