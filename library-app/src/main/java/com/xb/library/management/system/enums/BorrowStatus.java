package com.xb.library.management.system.enums;

import lombok.Getter;

/**
 * @author xiabiao
 * @date 2022-12-06
 */
@Getter
public enum BorrowStatus {

    /**
     * 待领取
     */
    UNCLAIMED(0),

    /**
     * 待归还
     */
    TO_RETURN(1),

    /**
     * 已归还
     */
    RETURNED(2);

    BorrowStatus(int code) {
        this.code = code;
    }

    private final int code;
}
