package com.xb.library.management.system.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.xb.library.management.system.domain.ApiResponse;
import com.xb.library.management.system.domain.SysConstant;
import com.xb.library.management.system.domain.User;
import com.xb.library.management.system.view.UserRegistryView;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author xiabiao
 * @date 2022-12-05
 */
@RequestMapping("/user")
public interface UserApi {

  /**
   * 保存用户
   *
   * @param user user
   * @return object
   */
  @PostMapping("/save")
  @PreAuthorize("hasRole('" + SysConstant.ROLE_ADMINISTRATOR + "')")
  ApiResponse<User> saveUser(@RequestBody @Valid User user);

  /**
   * 删除用户
   *
   * @param username username
   * @return no data
   */
  @DeleteMapping
  @PreAuthorize("hasRole('" + SysConstant.ROLE_ADMINISTRATOR + "')")
  ApiResponse<Void> delete(@RequestParam String username);

  /**
   * 注册用户
   *
   * @param user user
   * @return no data
   */
  @PostMapping("/signUp")
  ApiResponse<Void> register(@JsonView(UserRegistryView.class) @Valid @RequestBody User user);
}