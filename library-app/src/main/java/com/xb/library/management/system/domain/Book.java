package com.xb.library.management.system.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Date;

/** @author xiabiao */
@Validated
@Entity
@Data
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @NotEmpty(message = "name can't be empty.")
  @Size(max = 256, message = "name is too long.")
  private String name;

  @NotEmpty(message = "author can't be empty.")
  @Size(max = 256, message = "author is too long.")
  private String author;

  @NotNull(message = "price can't be null.")
  @Positive(message = "price must be positive.")
  private Float price;

  @NotEmpty(message = "isbn can't be empty.")
  @Size(max = 64, message = "isbn is too long.")
  private String isbn;

  private Integer borrower;

  @Column(insertable = false, updatable = false)
  private Date createTime;

  @Column(insertable = false)
  private Date updateTime;
}
