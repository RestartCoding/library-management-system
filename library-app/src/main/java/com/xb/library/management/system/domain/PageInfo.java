package com.xb.library.management.system.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @author xiabiao
 * @date 2022-12-02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo {

  @NotNull(message = "pageNum can't be null.")
  @Positive(message = "pageNum must be positive.")
  private Integer pageNum;

  @NotNull(message = "pageSize can't be null.")
  @Positive(message = "pageSize must be positive.")
  private Integer pageSize;
}
