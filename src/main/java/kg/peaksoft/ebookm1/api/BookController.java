package kg.peaksoft.ebookm1.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.ebookm1.dto.book.BookRequest;
import kg.peaksoft.ebookm1.dto.book.BookResponse;
import kg.peaksoft.ebookm1.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
@Tag(name = "Book", description = "The Book API")
public class BookController {

    private final BookService bookService;

    @Operation(summary = "User with role ADMIN can create")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public BookResponse createBook(@RequestBody BookRequest request) {
        return bookService.createBook(request);
    }

    @Operation(summary = "User with role ADMIN can update")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("{id}")
    public BookResponse updateBook(@PathVariable Long id, @RequestBody BookRequest request) {
        return bookService.updateBook(id, request);
    }

    @Operation(summary = "Allows all users to get a user by ID")
    @GetMapping("{id}")
    public BookResponse getByIdBook(@PathVariable Long id) {
        return bookService.getByIdBook(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "User with role ADMIN can delete")
    @DeleteMapping("{id}")
    public BookResponse deleteByIdBook(@PathVariable Long id) {
        return bookService.deleteByIdBook(id);
    }

    @Operation(summary = "Allows to get all users from the database")
    @GetMapping
    public List<BookResponse> getAllBooks() {
        return bookService.getAllBooks();
    }
 }
