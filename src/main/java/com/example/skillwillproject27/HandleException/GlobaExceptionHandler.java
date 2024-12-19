package com.example.skillwillproject27.HandleException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobaExceptionHandler {


    @ExceptionHandler(UserException.class)
    public ResponseEntity<ExceptionBody> handleUserNotFoundExcpetion(UserException notFoundException)
    {
        ExceptionBody exbody = new ExceptionBody();
        exbody.setMessage(notFoundException.getMessage());
        exbody.setStatus(ResponseEntity.status(HttpStatus.NOT_FOUND).build().getStatusCode());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exbody);
    }
}
