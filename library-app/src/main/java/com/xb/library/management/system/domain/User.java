package com.xb.library.management.system.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.xb.library.management.system.view.LoginView;
import com.xb.library.management.system.view.UserRegistryView;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

/**
 * @author xiabiao
 * @date 2022-12-05
 */
@Data
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotEmpty(message = "username can't be empty.")
  @Size(max = 64, message = "username is too long.")
  @JsonView({LoginView.class, UserRegistryView.class})
  private String username;

  @NotEmpty(message = "password can't be empty.")
  @JsonView({LoginView.class, UserRegistryView.class})
  private String password;

  @Column(insertable = false, updatable = false)
  private Date createTime;

  @Column(insertable = false)
  private Date updateTime;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return username.equals(user.username);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username);
  }
}
