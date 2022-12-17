package com.xb.library.management.system.api.vo;

import lombok.Data;

/**
 * @author xiabiao
 */
@Data
public class BookPageReq {

    private String name;

    private String author;

    private String isbn;

    private Boolean borrowable;
}
