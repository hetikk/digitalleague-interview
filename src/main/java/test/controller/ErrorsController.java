package test.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import test.model.exception.EntityNotFoundException;

@ControllerAdvice
public class ErrorsController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<RestError> entityNotFound(EntityNotFoundException ex, WebRequest request) {
        // handle Exception and Request if it needs
        return new ResponseEntity<>(new RestError("Entity not found"), HttpStatus.NOT_FOUND);
    }

    private record RestError(String message) {}

}
