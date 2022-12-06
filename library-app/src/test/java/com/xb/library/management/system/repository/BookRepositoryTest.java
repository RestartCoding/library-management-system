package com.xb.library.management.system.repository;

import com.xb.library.management.system.domain.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BookRepositoryTest {

  @Resource private BookRepository bookRepository;

  @Test
  public void testSave() {
    Book book = new Book();
    book.setId(1);
    book.setName("战争与和平");
    book.setAuthor("维克多.雨果");
    book.setPrice(10F);
    Date now = new Date();
    book.setCreateTime(now);
    book.setUpdateTime(now);

    bookRepository.save(book);

    assertNotNull(book.getId());
  }
}
