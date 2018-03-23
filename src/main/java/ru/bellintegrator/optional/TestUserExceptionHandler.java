package ru.bellintegrator.optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TestUserExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(TestUserException.class)
    protected ResponseEntity<TestException> handleTestUserException() {
        return new ResponseEntity<>(new TestException("Сочетание login/password не найдены"), HttpStatus.NOT_FOUND);
    }

    private static class TestException {
        private String message;

        TestException(){

        }

        public TestException(String message){
            this.message=message;
        }

        public String getMessage(){
            return message;
        }

        public void SetMessage(String message){
            this.message=message;
        }
    }
}
