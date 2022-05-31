package kg.peaksoft.ebookm1.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.ebookm1.api.playLoads.dto.book.BookRequest;
import kg.peaksoft.ebookm1.api.playLoads.dto.book.BookResponse;
import kg.peaksoft.ebookm1.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
@Tag(name = "Book", description = "The Book API")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class BookController {

    private final BookService bookService;

    @Operation(summary = "Method create audio book", description = "User with role VENDOR can create")
    @PreAuthorize("hasAnyAuthority('VENDOR')")
    @PostMapping("/createAudiobook")
    public BookResponse createAudioBook(@RequestBody BookRequest request) {
        return bookService.createAudioBook(request);
    }

    @Operation(summary = "Method create eBook", description = "User with role VENDOR can create")
    @PreAuthorize("hasAnyAuthority('VENDOR')")
    @PostMapping("/createEBook")
    public BookResponse createEBook(@RequestBody BookRequest request) {
        return bookService.createEBook(request);
    }

    @Operation(summary = "Method create paper book", description = "User with role VENDOR can create")
    @PreAuthorize("hasAnyAuthority('VENDOR')")
    @PostMapping("/createPaperBook")
    public BookResponse createPaperBook(@RequestBody BookRequest request) {
        return bookService.createPaperBook(request);
    }

    @Operation(summary = "Method update by id", description = "User with role ADMIN and VENDOR can update")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'VENDOR')")
    @PutMapping("{id}")
    public BookResponse updateBook(@PathVariable Long id, @RequestBody BookRequest request) {
        return bookService.updateBook(id, request);
    }

    @Operation(summary =  "Method get by id", description = "Allows all users to get a book by ID")
    @GetMapping("{id}")
    public BookResponse getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'VENDOR')")
    @Operation(summary = "Method delete by id", description = "User with role ADMIN and VENDOR can delete")
    @DeleteMapping("{id}")
    public BookResponse deleteBookById(@PathVariable Long id) {
        return bookService.deleteBookById(id);
    }

    @Operation(summary = "Method get all", description = "Allows to get all books from the database")
    @GetMapping
    public List<BookResponse> getAllBooks() {
        return bookService.getAllBooks();
    }
 }
