package com.shopping.shopping_site_backend.infra.sys.spring.exception;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler { @ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
  List<String> errors = ex.getBindingResult()
      .getAllErrors()
      .stream()
      .map(DefaultMessageSourceResolvable::getDefaultMessage)
      .collect(Collectors.toList());

  ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Validation Failed", errors);
  return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
}

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleGeneralException(Exception ex) {
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @RequiredArgsConstructor
  @Data
  public static class ErrorResponse {
    private int status;
    private String message;
    private Object details;

    public ErrorResponse(int status, String message, Object details) {
      this.status = status;
      this.message = message;
      this.details = details;
    }

  }}
