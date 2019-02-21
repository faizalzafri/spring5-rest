package com.faizal.spring5rest.exceptions.handlers;

import com.faizal.spring5rest.exceptions.NotFoundException;
import com.faizal.spring5rest.exceptions.NotValidException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResponseEntityExceptionHandler extends org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException() {
        return new ResponseEntity<>("Resource Not Found", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NotValidException.class})
    public ResponseEntity<Object> handleNotValidException() {
        return new ResponseEntity<>("Not Validate Test", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

}
