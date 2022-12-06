package com.xb.library.management.system.api;

import com.xb.library.management.system.domain.User;
import org.springframework.lang.Nullable;

/**
 * @author xiabiao
 * @date 2022-12-05
 */
public interface UserTokenManager {

  /**
   * 设置用户令牌
   *
   * @param user user info
   * @return token
   */
  String set(User user);

  /**
   * 通过令牌获取用户信息
   *
   * @param token token
   * @return user info
   */
  @Nullable
  User get(String token);

  /**
   * 清理token
   *
   * @param token token
   * @return true if success
   */
  boolean clear(String token);

  /**
   * 刷新token
   *
   * @param token token
   */
  void refresh(String token);
}
