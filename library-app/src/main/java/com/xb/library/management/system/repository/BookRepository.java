package com.xb.library.management.system.repository;

import com.xb.library.management.system.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

/**
 * @author xiabiao
 * @date 2022-12-02
 */
public interface BookRepository extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {

    /**
     * 根据书名删除数据
     *
     * @param name name
     * @return 删除记录数
     */
    int deleteByName(String name);

    /**
     * 书名查找
     *
     * @param name name
     * @return book list
     */
    List<Book> findAllByName(String name);

    /**
     * 根据isbn查找书籍
     *
     * @param isbn isbn
     * @return book
     */
    Optional<Book> findByIsbn(String isbn);
}
