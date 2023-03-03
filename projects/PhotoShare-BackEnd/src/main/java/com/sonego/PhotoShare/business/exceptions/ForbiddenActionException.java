package com.sonego.PhotoShare.business.exceptions;

public class ForbiddenActionException extends BusinessException {

    public ForbiddenActionException(String message) {
        super(message);
    }
}
