package ru.bellintegrator.optional;

public class Error {
    private Object error;

    public  Error(Object error)
    {
        this.error = error;
    }

    public Object getError() {
        return error;
    }
}
