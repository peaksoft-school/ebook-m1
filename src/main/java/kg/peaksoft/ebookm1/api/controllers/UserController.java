package kg.peaksoft.ebookm1.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.ebookm1.api.payloads.dto.book.BookResponse;
import kg.peaksoft.ebookm1.api.payloads.dto.user.UserRequest;
import kg.peaksoft.ebookm1.api.payloads.dto.user.UserResponse;
import kg.peaksoft.ebookm1.dataBase.entities.book.AudioBook;
import kg.peaksoft.ebookm1.dataBase.entities.book.Book;
import kg.peaksoft.ebookm1.dataBase.entities.book.EBook;
import kg.peaksoft.ebookm1.dataBase.entities.book.PaperBook;
import kg.peaksoft.ebookm1.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@Tag(name = "User", description = "The User API")
public class UserController {

    private final UserService userService;
    private final AudioBookService audioBookService;
    private final EBookService eBookService;
    private final PaperBookService paperBookService;
    private final BookService bookService;

    @Operation(summary = "Method create", description = "User with role ADMIN can create")
    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest request) {
        return userService.create(request);
    }

    @Operation(summary = "Method update", description = "User with role ADMIN can update")
    @PutMapping("{id}")
    public UserResponse updateUser(@PathVariable long id, @RequestBody UserRequest request) {
        return userService.update(request, id);
    }

    @Operation(summary = "Method get by id", description = "Allows all users to get a user by ID")
    @GetMapping("{id}")
    public UserResponse getByIdUser(@PathVariable long id) {
        return userService.getById(id);
    }

    @Operation(summary = "Method delete by id", description = "User with role ADMIN can delete")
    @DeleteMapping("{id}")
    public UserResponse deleteByIdUser(@PathVariable long id) {
        return userService.deleteById(id);
    }

    @Operation(summary = "Allows to get all users from the database")
    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    // Books
    @Operation(summary = "Method get all audio books", description = "Allows to get all audio books from the database")
    @GetMapping("/audio-books")
    public List<AudioBook>  getAllAudioBooks() {
        return audioBookService.getAllAudioBooks();
    }

    @Operation(summary = "Method get all electronic books", description = "Allows to get electronic books from the database")
    @GetMapping("/e-books")
    public List<EBook> getAllEbooks() {
        return eBookService.getAllEBooks();
    }

    @Operation(summary = "Method get all paper books", description = "Allows to get paper books from the database")
    @GetMapping("/paper-books")
    public List<PaperBook> getAllPaperBooks() {
        return paperBookService.getAllPaperBooks();
    }

    @Operation(summary = "Method get all books", description = "Allows to get all books from the database")
    @GetMapping("/books")
    public List<BookResponse> getAllBooks() {
        return bookService.getAllBooks();
    }

    @Operation(summary = "Method get by id", description = "Allows all users to get a book by ID")
    @GetMapping("/book/{id}")
    public BookResponse getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }
}
