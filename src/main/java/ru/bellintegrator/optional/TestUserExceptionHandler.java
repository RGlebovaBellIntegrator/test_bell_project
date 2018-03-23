package ru.bellintegrator.optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TestUserExceptionHandler extends ResponseEntityExceptionHandler  {
    @ExceptionHandler(value = {TestUserException.class})
    protected ResponseEntity<Data>  handleTestUserException(TestUserException ex) {
        return new ResponseEntity<> (new Data(ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
