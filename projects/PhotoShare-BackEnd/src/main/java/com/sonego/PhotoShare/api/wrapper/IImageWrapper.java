package com.sonego.PhotoShare.api.wrapper;

import com.sonego.PhotoShare.business.model.Image;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Component
public interface IImageWrapper {

    public Image toImage(MultipartFile imageMultiPartFile) throws IOException;
}
