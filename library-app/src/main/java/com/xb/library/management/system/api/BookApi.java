package com.xb.library.management.system.api;

import com.xb.library.management.system.domain.*;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * book api
 *
 * @author xiabiao
 */
@Api(value = "book")
@RequestMapping(value = "/book")
public interface BookApi {

  /**
   * 保存书籍
   *
   * @param book book
   * @return api response
   */
  @PostMapping
  @PreAuthorize("hasRole('" + SysConstant.ROLE_ADMINISTRATOR + "')")
  ResponseEntity<ApiResponse> saveBook(@RequestBody @Valid Book book);

  /**
   * 书籍分页
   *
   * @param pageInfo pageInfo
   * @return api response
   */
  @GetMapping("/page")
  @PreAuthorize("hasRole('" + SysConstant.ROLE_ADMINISTRATOR + "')")
  ResponseEntity<ApiResponse<ApiPage<Book>>> pageBook(@Valid PageInfo pageInfo);

  /**
   * 删除书籍
   *
   * @param name name
   * @return no data
   */
  @DeleteMapping
  @PreAuthorize("hasRole('" + SysConstant.ROLE_ADMINISTRATOR + "')")
  ResponseEntity<ApiResponse<Void>> remove(@RequestParam String name);
}
