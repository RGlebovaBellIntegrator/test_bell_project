package ru.bellintegrator.optional;

public class TestUserException extends RuntimeException {

    private String message;

    TestUserException(){
    }

    public TestUserException(String message){
        this.message=message;
    }

    public String getMessage(){
        return message;
    }

    public void SetMessage(String message){
        this.message=message;
    }
}
