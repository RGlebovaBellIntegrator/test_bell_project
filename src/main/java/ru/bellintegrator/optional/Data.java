package ru.bellintegrator.optional;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data {

    private Object data;

    private String error;

    public  Data(String error){
        this.error=error;
    }

    public  Data(Object data)
    {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error=error;
    }
}
