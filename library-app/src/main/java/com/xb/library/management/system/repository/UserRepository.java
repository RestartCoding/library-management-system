package com.xb.library.management.system.repository;

import com.xb.library.management.system.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author xiabiao
 * @date 2022-12-05
 */
public interface UserRepository extends JpaRepository<User, Integer> {
  /**
   * 通过用户名查找用户
   *
   * @param username username
   * @param password password
   * @return user
   */
  Optional<User> findByUsernameAndPassword(String username, String password);

  /**
   * 根据用户名删除用户
   *
   * @param username username
   * @return 删除记录数
   */
  int deleteByUsername(String username);

  /**
   * 根据用户名查找用户
   *
   * @param username username
   * @return user
   */
  Optional<User> findByUsername(String username);
}
