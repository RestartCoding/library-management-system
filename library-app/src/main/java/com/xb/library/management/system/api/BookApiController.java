package com.xb.library.management.system.api;

import com.google.common.util.concurrent.RateLimiter;
import com.xb.library.management.system.api.vo.BookPageReq;
import com.xb.library.management.system.domain.*;
import com.xb.library.management.system.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import java.util.concurrent.TimeUnit;

/**
 * @author xiabiao
 */
@RestController
public class BookApiController implements BookApi {

    private final BookService bookService;

    public BookApiController(BookService bookService) {
        this.bookService = bookService;
    }

    @SuppressWarnings("UnstableApiUsage")
    private final RateLimiter limiter = RateLimiter.create(100);

    @Override
    public ResponseEntity<ApiResponse<Void>> saveBook(Book book) {
        limiter.acquire();
        boolean success = limiter.tryAcquire(1, 0, TimeUnit.SECONDS);
        if (success) {
            bookService.saveBook(book);
            ApiResponse<Void> apiResponse = ApiResponse.<Void>builder().code(0).build();
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
    public ResponseEntity<ApiResponse<Void>> borrow(String isbn) {
        bookService.borrow(isbn);
        return ResponseEntity.ok(ApiResponse.<Void>builder().code(0).build());
    }

    @Override
    @PreAuthorize("hasRole('" + SysConstant.ROLE_ADMINISTRATOR + "')")
    public ApiResponse<Void> returnBook(String isbn) {
        bookService.returnBook(isbn);
        return ApiResponse.<Void>builder().code(0).message("return book success.").build();
    }

    @Override
    public ResponseEntity<ApiResponse<ApiPage<Book>>> borrowInfo(PageInfo pageInfo, BookPageReq req) {
        ApiPage<Book> page = bookService.page(pageInfo, req);
        return ResponseEntity.ok(ApiResponse.<ApiPage<Book>>builder().code(0).data(page).build());
    }
}
