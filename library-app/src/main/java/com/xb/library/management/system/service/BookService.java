package com.xb.library.management.system.service;

import com.xb.library.management.system.api.vo.BookPageReq;
import com.xb.library.management.system.domain.ApiPage;
import com.xb.library.management.system.domain.Book;
import com.xb.library.management.system.domain.PageInfo;

import java.util.List;

/**
 * @author xiabiao
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
     * @param req      req
     * @return 分页数据
     */
    ApiPage<Book> page(PageInfo pageInfo, BookPageReq req);

    /**
     * 删除书籍
     *
     * @param isbn isbn
     */
    void removeBook(String isbn);

    /**
     * 借书
     *
     * @param isbn isbn
     */
    void borrow(String isbn);

    /**
     * 还书
     *
     * @param isbn isbn
     */
    void returnBook(String isbn);

    /**
     * list popular book
     *
     * @return popular book list
     */
    List<Book> listPopular();
}
