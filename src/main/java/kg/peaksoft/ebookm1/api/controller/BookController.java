package kg.peaksoft.ebookm1.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.ebookm1.api.payLoad.dto.book.BookRequest;
import kg.peaksoft.ebookm1.api.payLoad.dto.book.BookResponse;
import kg.peaksoft.ebookm1.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book")
@Tag(name = "Book", description = "The Book API")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class BookController {

    private final BookService service;

    @PostMapping("/create")
    @Operation(summary = "A user with the Admin role can create")
    public BookResponse createBook(@RequestBody BookRequest request) {
        return service.create(request);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "A user with the Admin role can update")
    public BookResponse updateByIdBook(@PathVariable Long id, BookRequest request) {
        return service.update(id, request);
    }

    @GetMapping("/getById/{id}")
    @Operation(summary = "All users can get by id")
    public BookResponse getByIdBook(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/deleteById/{id}")
    @Operation(summary = "A user with the Admin role can delete")
    public BookResponse deleteByIdBook(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @GetMapping("/getAll")
    @Operation(summary = "All users can get all")
    public List<BookResponse> getAllBooks() {
        return service.getAll();
    }
}
