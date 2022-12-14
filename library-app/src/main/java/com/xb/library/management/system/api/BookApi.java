package com.xb.library.management.system.api;

import com.xb.library.management.system.api.vo.BookPageReq;
import com.xb.library.management.system.domain.*;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    ResponseEntity<ApiResponse<Void>> saveBook(@RequestBody @Valid Book book);

    /**
     * 书籍分页
     *
     * @param pageInfo pageInfo
     * @param req      req
     * @return api response
     */
    @GetMapping("/page")
//    @PreAuthorize("hasRole('" + SysConstant.ROLE_ADMINISTRATOR + "')")
    ResponseEntity<ApiResponse<ApiPage<Book>>> pageBook(@Valid PageInfo pageInfo, BookPageReq req);

    /**
     * 删除书籍
     *
     * @param isbn isbn
     * @return no data
     */
    @DeleteMapping
    @PreAuthorize("hasRole('" + SysConstant.ROLE_ADMINISTRATOR + "')")
    ResponseEntity<ApiResponse<Void>> remove(@RequestParam String isbn);

    /**
     * 借书
     *
     * @param isbn isbn
     * @return no data
     */
    @PutMapping("/borrowing")
    ResponseEntity<ApiResponse<Void>> borrow(@RequestParam String isbn);

    /**
     * 还书
     *
     * @param isbn isbn
     * @return no data
     */
    @PutMapping("/returnBook")
    ApiResponse<Void> returnBook(@RequestParam String isbn);

    /**
     * 我的借阅信息
     *
     * @param pageInfo pageInfo
     * @param req      req
     * @return 我的借阅书籍
     */
    @GetMapping("/borrowInfo")
    ResponseEntity<ApiResponse<ApiPage<Book>>> borrowInfo(@Valid PageInfo pageInfo, @Valid BookPageReq req);

    /**
     * list popular book
     *
     * @return popular book list
     */
    @GetMapping("/popularList")
    ApiResponse<List<Book>> listPopular();
}
