package com.xb.library.management.system.repository;

import com.xb.library.management.system.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xiabiao
 * @date 2022-12-05
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

  /**
   * 根据用户名查询
   *
   * @param username username
   * @return 用户角色信息
   */
  List<UserRole> findAllByUsername(String username);
}
