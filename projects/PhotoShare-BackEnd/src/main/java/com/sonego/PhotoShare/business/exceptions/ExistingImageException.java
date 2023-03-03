package com.sonego.PhotoShare.business.exceptions;

import com.sonego.PhotoShare.business.model.Image;
import com.sonego.PhotoShare.business.model.Profile;

import java.util.UUID;

public class ExistingImageException extends BusinessException {

    public ExistingImageException(String message) {
        super(message);
    }
}
