package com.mitsko.mrdb.service;

public class UserException extends ServiceException {
    public UserException() {
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(Exception ex) {
        super(ex);
    }

    public UserException(String message, Exception ex) {
        super(message, ex);
    }
}
