package kg.peaksoft.ebookm1.api;

import kg.peaksoft.ebookm1.dto.book.BookRequest;
import kg.peaksoft.ebookm1.dto.book.BookResponse;
import kg.peaksoft.ebookm1.dto.customer.CustomerRequest;
import kg.peaksoft.ebookm1.dto.customer.CustomerResponse;
import kg.peaksoft.ebookm1.service.BookService;
import kg.peaksoft.ebookm1.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public BookResponse addBook(@RequestBody BookRequest request) {
        return bookService.createBook(request);
    }


    @PutMapping("{id}")
    public BookResponse updateBook(@PathVariable long id,
                                   @RequestBody BookRequest request) {
        return bookService.update(request,id);
    }

    @GetMapping("{id}")
    public  BookResponse getBookById(@PathVariable long id,
                                    @RequestBody BookRequest request) {
        return bookService.getBookById(id);
    }


//
//    @DeleteMapping("{id}")
//    public CustomerResponse deleteByIdUser(@PathVariable long id,
//                                       @RequestBody CustomerRequest request) {
//        return userService.deleteById(id);
//    }
//
//    @GetMapping
//    public List<CustomerResponse> getAllUsers() {
//        return userService.getAllUsers();
//    }

}
