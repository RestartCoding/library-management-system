package com.xb.library.management.system.repository;

import com.xb.library.management.system.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xiabiao
 * @date 2022-12-02
 */
public interface BookRepository extends JpaRepository<Book, Integer> {

  /**
   * 根据书名删除数据
   *
   * @param name name
   * @return 删除记录数
   */
  int deleteByName(String name);
}
