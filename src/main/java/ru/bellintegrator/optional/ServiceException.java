package ru.bellintegrator.optional;

public class ServiceException extends RuntimeException {

    private String message;

    ServiceException(){
    }

    public ServiceException(String message){
        this.message=message;
    }

    public String getMessage(){
        return message;
    }

    public void SetMessage(String message){
        this.message=message;
    }
}
