package com.mitsko.mrdb.service;

public class ServiceException extends Exception{
    public ServiceException(){}

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(Exception ex){
        super(ex);
    }

    public ServiceException(String message, Exception ex){
        super(message, ex);
    }
}
