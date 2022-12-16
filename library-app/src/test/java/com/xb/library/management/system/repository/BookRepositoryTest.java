package com.xb.library.management.system.repository;

import com.xb.library.management.system.domain.Book;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BookRepositoryTest {

    @Resource
    private BookRepository bookRepository;

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

    @Test
    public void test() {
        Specification<Book> specification = new Specification<Book>() {
            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> pridicates = new ArrayList<>();
                pridicates.add(criteriaBuilder.like(root.get("name"), "Thinking In%"));
                return criteriaBuilder.and(pridicates.toArray(new Predicate[0]));
            }
        };
        List<Book> books = bookRepository.findAll(specification);
        Assert.assertTrue(books.size() > 0);
    }
}
