package com.xb.library.management.system.domain;

import com.xb.library.management.system.enums.BorrowStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author jack
 */
@Data
@Entity
public class BorrowInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String isbn;

    private String bookName;

    /**
     * 借入时间
     */
    private Date borrowTime;

    /**
     * 归还时间
     */
    private Date returnTime;

    @Column(updatable = false, insertable = false)
    private Date createTime;

    @Column(insertable = false)
    private Date updateTime;
}
