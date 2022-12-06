package com.xb.library.management.system.api;

import com.xb.library.management.system.domain.ApiPage;
import com.xb.library.management.system.domain.ApiResponse;
import com.xb.library.management.system.domain.Book;
import com.xb.library.management.system.domain.PageInfo;
import com.xb.library.management.system.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

/** @author xiabiao */
@Controller
public class BookApiController implements BookApi {

  private BookService bookService;

  public BookApiController(BookService bookService) {
    this.bookService = bookService;
  }

  @Override
  public ResponseEntity<ApiResponse> saveBook(Book book) {
    bookService.saveBook(book);
    ApiResponse apiResponse = ApiResponse.builder().code(0).build();
    return ResponseEntity.ok(apiResponse);
  }

  @Override
  public ResponseEntity<ApiResponse<ApiPage<Book>>> pageBook(PageInfo pageInfo) {
    ApiPage<Book> page = bookService.page(pageInfo);
    ApiResponse<ApiPage<Book>> apiResponse =
        ApiResponse.<ApiPage<Book>>builder().code(0).data(page).build();
    return ResponseEntity.ok().body(apiResponse);
  }

  @Override
  public ResponseEntity<ApiResponse<Void>> remove(String name) {
    bookService.removeBook(name);
    return ResponseEntity.ok(ApiResponse.<Void>builder().code(0).build());
  }
}
