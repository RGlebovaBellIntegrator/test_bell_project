package ru.bellintegrator.optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler  {

    @ExceptionHandler(value = {ServiceException.class})
    @ResponseBody
    public ResponseEntity<Data> handleServiceException(ServiceException ex) {
        return new ResponseEntity<> (new Data(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

}
