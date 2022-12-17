package com.xb.library.management.system.service.impl;

import com.xb.library.management.system.api.vo.BookPageReq;
import com.xb.library.management.system.domain.ApiPage;
import com.xb.library.management.system.domain.Book;
import com.xb.library.management.system.domain.BorrowInfo;
import com.xb.library.management.system.domain.PageInfo;
import com.xb.library.management.system.exception.BusinessException;
import com.xb.library.management.system.repository.BookRepository;
import com.xb.library.management.system.repository.BorrowInfoRepository;
import com.xb.library.management.system.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author xiabiao
 */
@Service
public class BookServiceImpl implements BookService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);

    @Resource
    private BookRepository bookRepository;

    @Resource
    private BorrowInfoRepository borrowInfoRepository;


    @Override
    public void saveBook(Book book) {
        book.setBorrowable(true);
        bookRepository.save(book);
    }

    @Override
    public ApiPage<Book> page(PageInfo pageInfo, BookPageReq req) {
        Specification<Book> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(req.getName())) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + req.getName() + "%"));
            }
            if (StringUtils.hasText(req.getAuthor())) {
                predicates.add(criteriaBuilder.like(root.get("author"), "%" + req.getAuthor() + "%"));
            }
            if (StringUtils.hasText(req.getIsbn())) {
                predicates.add(criteriaBuilder.like(root.get("isbn"), "%" + req.getIsbn() + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        Page<Book> page =
                bookRepository.findAll(specification, PageRequest.of(pageInfo.getPageNum() - 1, pageInfo.getPageSize()));
        return ApiPage.fromPage(page);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void removeBook(String isbn) {
        Optional<BorrowInfo> optionalBorrowInfo = borrowInfoRepository.findByIsbnAndReturnTimeIsNull(isbn);
        if (optionalBorrowInfo.isPresent()) {
            LOGGER.error("Failed to delete book [{}].The book is lent out.", isbn);
            throw new BusinessException("The book is lent out.");
        }
        int rows = bookRepository.deleteByIsbn(isbn);
        if (rows == 0) {
            LOGGER.error("Failed to delete book [{}].The book does not exist.", isbn);
            throw new BusinessException("The book does not exist.");
        }
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void borrow(String isbn) {

        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
        if (!optionalBook.isPresent()) {
            LOGGER.error("Failed to borrow book [{}]. The book does not exist.", isbn);
            throw new BusinessException("The book does not exist.");
        }

        Specification<BorrowInfo> specification = (root, query, criteriaBuilder) ->
                criteriaBuilder.and(criteriaBuilder.isNull(root.get("returnTime")),
                        criteriaBuilder.and(criteriaBuilder.equal(root.get("isbn"), isbn)));

        long count = borrowInfoRepository.count(specification);
        if (count > 0) {
            LOGGER.error("Failed to borrow book [{}]. The book already be borrowed.", isbn);
            throw new BusinessException("The book already be borrowed.");
        }

        optionalBook.get().setBorrowable(false);
        bookRepository.saveAndFlush(optionalBook.get());

        BorrowInfo borrowInfo = new BorrowInfo();
        borrowInfo.setUsername(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        borrowInfo.setIsbn(isbn);
        borrowInfo.setBookName(optionalBook.get().getName());
        borrowInfo.setBorrowTime(new Date());
        borrowInfoRepository.saveAndFlush(borrowInfo);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void returnBook(String isbn) {

        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
        if (!optionalBook.isPresent()) {
            LOGGER.error("Failed to return book. The book [{}] does not exist.", isbn);
            throw new BusinessException("The book does not exist.");
        }

        Optional<BorrowInfo> optionalBorrowInfo = borrowInfoRepository.findByIsbnAndReturnTimeIsNull(isbn);
        if (!optionalBorrowInfo.isPresent()) {
            String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            LOGGER.error("Failed to return book. The user [{}] does not borrow book [{}]", username, isbn);
            throw new BusinessException("The user does not borrow book.");
        }

        BorrowInfo borrowInfo = optionalBorrowInfo.get();
        borrowInfo.setReturnTime(new Date());

        Book book = optionalBook.get();
        book.setBorrowable(true);

        borrowInfoRepository.saveAndFlush(borrowInfo);

        bookRepository.saveAndFlush(book);
    }
}
