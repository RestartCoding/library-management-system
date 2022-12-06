package com.xb.library.management.system.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.Collection;

/**
 * @author xiabiao
 * @date 2022-12-06
 */
@Data
@Builder
public class ApiPage<E> {

  private int pageNum;

  private int pageSize;

  private long total;

  private int pages;

  private Collection<E> list;

  public static <T> ApiPage<T> fromPage(Page<T> page) {
    return ApiPage.<T>builder()
        .pageNum(page.getNumber() + 1)
        .pageSize(page.getSize())
        .total(page.getTotalElements())
        .pages(page.getTotalPages())
        .list(page.getContent())
        .build();
  }
}
