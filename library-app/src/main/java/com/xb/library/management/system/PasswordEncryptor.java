package com.xb.library.management.system;

import org.springframework.util.DigestUtils;

/**
 * @author xiabiao
 * @date 2022-12-05
 */
public class PasswordEncryptor {

  /**
   * 加密（摘要）密码
   *
   * @param password password
   * @return password
   */
  public static String encrypt(String password) {
    return DigestUtils.md5DigestAsHex(password.getBytes());
  }

  private PasswordEncryptor() {
    throw new AssertionError();
  }
}
