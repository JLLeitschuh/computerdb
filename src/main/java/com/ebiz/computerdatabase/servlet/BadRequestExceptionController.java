package com.ebiz.computerdatabase.servlet;

import com.ebiz.computerdatabase.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by ebiz on 22/06/17.
 */
@ControllerAdvice
public class BadRequestExceptionController {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequest(Exception ex){

        return new ResponseEntity(ex.toString(), HttpStatus.BAD_REQUEST);

    }
}
