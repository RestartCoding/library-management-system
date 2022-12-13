package com.xb.library.management.system.service.impl;

import com.xb.library.management.system.api.vo.BookPageReq;
import com.xb.library.management.system.domain.ApiPage;
import com.xb.library.management.system.domain.Book;
import com.xb.library.management.system.domain.PageInfo;
import com.xb.library.management.system.exception.BusinessException;
import com.xb.library.management.system.repository.BookRepository;
import com.xb.library.management.system.service.BookService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author xiabiao
 * @date 2022-12-02
 */
@Service
public class BookServiceImpl implements BookService {

    @Resource
    private BookRepository bookRepository;

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public ApiPage<Book> page(PageInfo pageInfo, BookPageReq req) {
        Book book = new Book();
        book.setBorrower(req.getBorrower());
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("borrower", ExampleMatcher.GenericPropertyMatcher::exact);
        Example<Book> example = Example.of(book, matcher);
        Page<Book> page =
                bookRepository.findAll(example, PageRequest.of(pageInfo.getPageNum() - 1, pageInfo.getPageSize()));
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

    @Override
    public void borrow(String bookName) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Book> name = bookRepository.findAllByName(bookName);
        Book book = name.stream().filter(e -> StringUtils.isEmpty(e.getBorrower())).findAny().orElse(null);
        if (book != null) {
            book.setBorrower(username);
            bookRepository.save(book);
            return;
        }
        throw new BusinessException("Sorry, no book to borrow.");
    }
}
