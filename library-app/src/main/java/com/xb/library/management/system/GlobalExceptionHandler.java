package com.xb.library.management.system;

import com.xb.library.management.system.domain.ApiResponse;
import com.xb.library.management.system.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiabiao
 * @date 2022-12-02
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse> exception(Exception e) {
    LOGGER.error(e.getMessage(), e);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(ApiResponse.builder().code(-1).message("system error").build());
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ApiResponse<String>> handle(BusinessException e) {
    LOGGER.error(e.getMessage(), e);
    return ResponseEntity.ok(
        ApiResponse.<String>builder().code(-1).message(e.getMessage()).build());
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ApiResponse> handle(AccessDeniedException e) {
    LOGGER.error(e.getMessage(), e);
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(
            ApiResponse.<String>builder()
                .code(-1)
                .message(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .build());
  }

  @ExceptionHandler(BindException.class)
  public ApiResponse<Void> handle(BindException e) {
    List<ObjectError> errors = e.getBindingResult().getAllErrors();
    String message =
        errors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(";"));
    return ApiResponse.<Void>builder().code(-1).message(message).build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ApiResponse<Void> handle(MethodArgumentNotValidException e) {
    List<FieldError> errors = e.getBindingResult().getFieldErrors();
    String message =
        errors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(";"));
    return ApiResponse.<Void>builder().code(-1).message(message).build();
  }
}
