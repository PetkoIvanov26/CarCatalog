package com.petkoivanov.carCatalog.handlers;

import com.petkoivanov.carCatalog.exceptions.EntityAlreadyExistsException;
import com.petkoivanov.carCatalog.exceptions.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.UnexpectedTypeException;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.petkoivanov.carCatalog.utils.ExceptionMessages.CAUGHT_EXCEPTION;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Map<String, List<String>>> handleIllegalArgumentException(IllegalArgumentException exception) {
    log.error(CAUGHT_EXCEPTION, exception);

    Map<String, List<String>> errorsMap = formatErrorsResponse(exception.getMessage());

    return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UnsatisfiedServletRequestParameterException.class)
  public ResponseEntity<Map<String, List<String>>> handleUnsatisfiedServletRequestParameterException(
    UnsatisfiedServletRequestParameterException exception) {
    log.error(CAUGHT_EXCEPTION, exception);

    Map<String, List<String>> errorsMap = formatErrorsResponse(exception.getMessage());

    return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NullPointerException.class)
  public ResponseEntity<Map<String, List<String>>> handleNullPointerException(NullPointerException exception) {
    log.error(CAUGHT_EXCEPTION, exception);

    Map<String, List<String>> errorsMap = formatErrorsResponse(exception.getMessage());

    return new ResponseEntity<>(errorsMap, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(UnexpectedTypeException.class)
  public ResponseEntity<Map<String, List<String>>> handleUnexpectedTypeException(UnexpectedTypeException exception) {
    log.error(CAUGHT_EXCEPTION, exception);

    Map<String, List<String>> errorsMap = formatErrorsResponse(exception.getMessage());

    return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Map<String, List<String>>> handleHttpMessageNotReadableException(
    HttpMessageNotReadableException exception) {
    log.error(CAUGHT_EXCEPTION, exception);

    Map<String, List<String>> errorsMap = formatErrorsResponse(exception.getMessage());

    return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<Map<String, List<String>>> handleHttpRequestMethodNotSupportedException(
    HttpRequestMethodNotSupportedException exception) {
    log.error(CAUGHT_EXCEPTION, exception);

    Map<String, List<String>> errorsMap = formatErrorsResponse(exception.getMessage());

    return new ResponseEntity<>(errorsMap, HttpStatus.METHOD_NOT_ALLOWED);
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<Map<String, List<String>>> handleMissingServletRequestParameterException(
    MissingServletRequestParameterException exception) {
    log.error(CAUGHT_EXCEPTION, exception);

    Map<String, List<String>> errorsMap = formatErrorsResponse(exception.getMessage());

    return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MissingPathVariableException.class)
  public ResponseEntity<Map<String, List<String>>> handleMissingPathVariableException(
    MissingPathVariableException exception) {
    log.error(CAUGHT_EXCEPTION, exception);

    Map<String, List<String>> errorsMap = formatErrorsResponse(exception.getMessage());

    return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DateTimeParseException.class)
  public ResponseEntity<Map<String, List<String>>> handleDateTimeParseException(DateTimeParseException exception) {
    log.error(CAUGHT_EXCEPTION, exception);

    Map<String, List<String>> errorsMap = formatErrorsResponse(exception.getMessage());

    return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, List<String>>> handleControllerValidationException(
    MethodArgumentNotValidException exception) {

    log.error("Caught exception: ", exception);

    List<String> errors = exception.getBindingResult().getFieldErrors().stream()
                                   .map(FieldError::getDefaultMessage)
                                   .collect(Collectors.toList());
    return new ResponseEntity<>(formatErrorsResponse(errors), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<Map<String, List<String>>> handleMethodArgumentTypeMismatchException(
    MethodArgumentTypeMismatchException exception) {
    log.error(CAUGHT_EXCEPTION, exception);

    Map<String, List<String>> errorsMap = formatErrorsResponse(exception.getMessage());

    return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Map<String , List<String>>> handleEntityNotFoundException(EntityNotFoundException exception){
    log.error(CAUGHT_EXCEPTION,exception);

    Map<String , List<String>> errorsMap = formatErrorsResponse(exception.getMessage());

    return new ResponseEntity<>(errorsMap , HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(EntityAlreadyExistsException.class)
  public ResponseEntity<Map<String , List<String>>> handleEntityAlreadyExistsException(EntityAlreadyExistsException exception){
    log.error(CAUGHT_EXCEPTION,exception);

    Map<String , List<String>> errorsMap = formatErrorsResponse(exception.getMessage());

    return new ResponseEntity<>(errorsMap , HttpStatus.BAD_REQUEST);
  }

  private Map<String, List<String>> formatErrorsResponse(String... errors) {
    return formatErrorsResponse(Arrays.stream(errors).collect(Collectors.toList()));
  }

  private Map<String, List<String>> formatErrorsResponse(List<String> errors) {
    Map<String, List<String>> errorResponse = new HashMap<>(4);
    errorResponse.put("Errors", errors);
    return errorResponse;
  }
}
