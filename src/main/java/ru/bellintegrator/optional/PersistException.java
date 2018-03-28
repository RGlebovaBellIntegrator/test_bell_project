package ru.bellintegrator.optional;

import java.text.MessageFormat;

public class PersistException extends RuntimeException {

    private String message;

    private Exception ex;

    PersistException(){
    }

    public PersistException(String message, Exception ex){
        this.message=message;
        this.ex = ex;
    }

    public String getMessage(){
        MessageFormat fmt = new MessageFormat("{0}\n{1}");
        return fmt.format( message, ex);
    }

    public void SetMessage(String message){
        this.message=message;
    }
}
