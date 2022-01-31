package com.rabouk.bookstore.domain.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
import java.util.List;

/**
 * @author : Raouf Bouklab
 * @created : 2022-01-15, Saturday
 **/

@Slf4j
@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<String> handleExceptions(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(HttpServletRequest request, NotFoundException exception) {
        logger.error("NotFoundException " + request.getRequestURI(), exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FoundException.class)
    public ResponseEntity<String> handleFoundException(HttpServletRequest request, FoundException exception) {
        logger.error("FoundException " + request.getRequestURI(), exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(HttpServletRequest request, ValidationException exception) {
        logger.error("ValidationException " + request.getRequestURI(), exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(HttpServletRequest request, AccessDeniedException ex) {
        logger.error("handleAccessDeniedException " + request.getRequestURI(), ex);
        return new ResponseEntity<>("Access denied!", HttpStatus.FORBIDDEN);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<FieldErrorMessage> fieldErrorMessages = fieldErrors
                .stream()
                .map(fieldError -> new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();
        log.error("Invalid fields: {}", fieldErrorMessages);
        return new ResponseEntity<>(fieldErrorMessages, HttpStatus.BAD_REQUEST);
    }
}
