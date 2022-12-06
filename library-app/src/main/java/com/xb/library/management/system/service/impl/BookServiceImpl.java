package com.xb.library.management.system.service.impl;

import com.xb.library.management.system.domain.ApiPage;
import com.xb.library.management.system.domain.Book;
import com.xb.library.management.system.domain.PageInfo;
import com.xb.library.management.system.exception.BusinessException;
import com.xb.library.management.system.repository.BookRepository;
import com.xb.library.management.system.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * @author xiabiao
 * @date 2022-12-02
 */
@Service
public class BookServiceImpl implements BookService {

  @Resource private BookRepository bookRepository;

  @Override
  public void saveBook(Book book) {
    bookRepository.save(book);
  }

  @Override
  public ApiPage<Book> page(PageInfo pageInfo) {
    Page<Book> page =
        bookRepository.findAll(PageRequest.of(pageInfo.getPageNum() - 1, pageInfo.getPageSize()));
    return ApiPage.fromPage(page);
  }

  @Override
  @Transactional(rollbackOn = Exception.class)
  public void removeBook(String name) {
    int rows = bookRepository.deleteByName(name);
    if (rows == 0) {
      throw new BusinessException("The book does not exist.");
    }
  }
}
