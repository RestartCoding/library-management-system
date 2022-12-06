package com.xb.library.management.system.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;

/**
 * @author xiabiao
 * @date 2022-12-02
 */
@Data
@Builder
public class Page<E> {

    private int pageNum;

    private int pageSize;

    private long total;

    private Collection<E> data;
}
