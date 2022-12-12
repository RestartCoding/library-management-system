package com.xb.library.management.system.api;

import com.google.common.util.concurrent.RateLimiter;
import com.xb.library.management.system.api.vo.BookPageReq;
import com.xb.library.management.system.domain.*;
import com.xb.library.management.system.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpServerErrorException;

import java.util.concurrent.TimeUnit;

/**
 * @author xiabiao
 */
@Controller
public class BookApiController implements BookApi {

    private BookService bookService;

    public BookApiController(BookService bookService) {
        this.bookService = bookService;
    }

    @SuppressWarnings("UnstableApiUsage")
    private final RateLimiter limiter = RateLimiter.create(100);

    @Override
    public ResponseEntity<ApiResponse> saveBook(Book book) {
        limiter.acquire();
        boolean success = limiter.tryAcquire(1, 0, TimeUnit.SECONDS);
        if (success) {
            bookService.saveBook(book);
            ApiResponse apiResponse = ApiResponse.builder().code(0).build();
            return ResponseEntity.ok(apiResponse);
        }
        throw new HttpServerErrorException(HttpStatus.REQUEST_TIMEOUT);
    }

    @Override
    public ResponseEntity<ApiResponse<ApiPage<Book>>> pageBook(PageInfo pageInfo, BookPageReq req) {
        ApiPage<Book> page = bookService.page(pageInfo, req);
        ApiResponse<ApiPage<Book>> apiResponse =
                ApiResponse.<ApiPage<Book>>builder().code(0).data(page).build();
        return ResponseEntity.ok().body(apiResponse);
    }

    @Override
    public ResponseEntity<ApiResponse<Void>> remove(String name) {
        bookService.removeBook(name);
        return ResponseEntity.ok(ApiResponse.<Void>builder().code(0).build());
    }

    @Override
    public ResponseEntity<ApiResponse<Void>> borrow(String bookName) {
        bookService.borrow(bookName);
        return ResponseEntity.ok(ApiResponse.<Void>builder().code(0).build());
    }

    @Override
    public ResponseEntity<ApiResponse<ApiPage<Book>>> borrowInfo(PageInfo pageInfo, BookPageReq req) {
        req.setBorrower(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        ApiPage<Book> page = bookService.page(pageInfo, req);
        return ResponseEntity.ok(ApiResponse.<ApiPage<Book>>builder().code(0).data(page).build());
    }
}
