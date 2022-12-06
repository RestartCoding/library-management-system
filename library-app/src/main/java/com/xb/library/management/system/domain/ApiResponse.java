package com.xb.library.management.system.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/** @author xiabiao */
@Validated
@Data
@Builder
public class ApiResponse<E> {
  private Integer code;

  private String type;

  private String message;

  private E data;
}
