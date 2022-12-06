package com.xb.library.management.system.service;

import com.xb.library.management.system.domain.ApiPage;
import com.xb.library.management.system.domain.Book;
import com.xb.library.management.system.domain.PageInfo;
import org.springframework.data.domain.Page;

/**
 * @author xiabiao
 * @date 2022-12-02
 */
public interface BookService {
  /**
   * 保存书籍
   *
   * @param book book
   */
  void saveBook(Book book);

  /**
   * 分页查询数据
   *
   * @param pageInfo pageInfo
   * @return 分页数据
   */
  ApiPage<Book> page(PageInfo pageInfo);

  /**
   * 删除书籍
   *
   * @param name 书名
   */
  void removeBook(String name);
}
