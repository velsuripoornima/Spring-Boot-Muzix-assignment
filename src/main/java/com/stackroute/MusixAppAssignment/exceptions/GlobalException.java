package com.stackroute.MusixAppAssignment.exceptions;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice(basePackages = "com.stackroute.springboot")
public class GlobalException {

    private ResponseEntity<VndErrors> error(final Exception exception, final HttpStatus httpStatus, final String logRef)
    {
        final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return new ResponseEntity<>(new VndErrors(logRef, message), httpStatus);
    }
    @ExceptionHandler(TrackNotFoundException.class)
    public ResponseEntity <VndErrors> notFoundException(final TrackNotFoundException e)
    {
        return error(e, HttpStatus.NOT_FOUND, e.getMessage());
    }
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity <VndErrors> alreadyExistsException(final UserAlreadyExistException e) {
        return error(e, HttpStatus.CONFLICT, e.getLocalizedMessage());
    }

}
