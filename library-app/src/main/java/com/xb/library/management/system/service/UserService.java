package com.xb.library.management.system.service;

import com.xb.library.management.system.domain.User;
import com.xb.library.management.system.exception.BusinessException;
import org.springframework.lang.NonNull;

/**
 * @author xiabiao
 * @date 2022-12-05
 */
public interface UserService {

  /**
   * 保存用户
   *
   * @param user user
   */
  void saveUser(User user);

  /**
   * 删除用户
   *
   * @param username username
   * @throws BusinessException BusinessException
   */
  void deleteUser(String username) throws BusinessException;

  /**
   * 登录
   *
   * @param username username
   * @param password password
   * @throws BusinessException BusinessException
   * @return user
   */
  @NonNull
  User login(String username, String password) throws BusinessException;

  /**
   * 注册用户
   *
   * @param user user
   */
  void register(User user);
}
