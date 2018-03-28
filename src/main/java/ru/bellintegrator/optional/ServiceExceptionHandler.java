package ru.bellintegrator.optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler  {

    @ExceptionHandler(value = {NoFoundException.class})
    @ResponseBody
    public ResponseEntity<Data> handleServiceException(NoFoundException ex) {
        return new ResponseEntity<> (new Data(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {PersistException.class})
    @ResponseBody
    public ResponseEntity<Data> handleServiceException(PersistException ex) {
        return new ResponseEntity<> (new Data(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
