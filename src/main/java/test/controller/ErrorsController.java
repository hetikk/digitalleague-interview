package test.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import test.model.exception.EntityNotFoundException;

@ControllerAdvice
public class ErrorsController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> entityNotFound(EntityNotFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, new RestError("Entity not found"),
                new HttpHeaders(), HttpStatus.NO_CONTENT, request);
    }

    private record RestError(String message) {}

}
