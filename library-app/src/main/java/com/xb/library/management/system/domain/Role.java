package com.xb.library.management.system.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author xiabiao
 * @date 2022-12-05
 */
@Entity
@Data
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String name;

  private Date createTime;

  private Date updateTime;
}
