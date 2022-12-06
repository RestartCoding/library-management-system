package com.xb.library.management.system.domain;

import java.util.Objects;

/**
 * @author xiabiao
 * @date 2022-12-05
 */
public class Token {

  /** 1 day */
  public static final int ACTIVE_SECONDS = 60 * 60 * 24;

  private String token;

  private Long expiredTimeSeconds;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Long getExpiredTimeSeconds() {
    return expiredTimeSeconds;
  }

  public void setExpiredTimeSeconds(Long expiredTimeSeconds) {
    this.expiredTimeSeconds = expiredTimeSeconds;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Token token1 = (Token) o;
    return token.equals(token1.token);
  }

  @Override
  public int hashCode() {
    return Objects.hash(token);
  }

    public Token() {
    }

    public Token(String token) {
        this.token = token;
    }
}
