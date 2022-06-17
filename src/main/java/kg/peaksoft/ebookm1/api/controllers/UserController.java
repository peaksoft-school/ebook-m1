package kg.peaksoft.ebookm1.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.ebookm1.api.controllers.payloads.dto.book.BookResponse;
import kg.peaksoft.ebookm1.db.enums.TypeOfBook;
import kg.peaksoft.ebookm1.api.controllers.payloads.dto.user.UserRequest;
import kg.peaksoft.ebookm1.api.controllers.payloads.dto.user.UserResponse;
import kg.peaksoft.ebookm1.db.services.BookService;
import kg.peaksoft.ebookm1.db.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@Tag(name = "User", description = "The User API")
public class UserController {

    private final UserService userService;
    private final BookService bookService;

    @Operation(summary = "Method create", description = "User with role ADMIN can create")
    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest request) {
        log.info("We will remove this method from this controller");
        return userService.create(request);
    }

    @Operation(summary = "Method update", description = "User with role ADMIN can update")
    @PutMapping("{id}")
    public UserResponse updateUser(@PathVariable long id, @RequestBody UserRequest request) {
        log.info("Inside Client controller update client method");
        return userService.update(request, id);
    }

    @Operation(summary = "Method get by id", description = "Allows all users to get a user by ID")
    @GetMapping("{id}")
    public UserResponse getByIdUser(@PathVariable long id) {
        log.info("We will remove this method from this controller");
        return userService.getById(id);
    }

    @Operation(summary = "Method delete by id", description = "User with role ADMIN can delete")
    @DeleteMapping("{id}")
    public UserResponse deleteByIdUser(@PathVariable long id) {
        log.info("Inside Client controller delete by id client method");
        return userService.deleteById(id);
    }

    @Operation(summary = "Allows to get all users from the database")
    @GetMapping
    public List<UserResponse> getAllUsers() {
        log.info("We will remove this method from this controller");
        return userService.getAllUsers();
    }

    // Books
    @Operation(summary = "Method get all books by type",
            description = "Allows to get all type books {AUDIO_BOOK,PAPER_BOOK,E_BOOK} from the database")
    @GetMapping("/books/type")
    public List<BookResponse> getAllBooksByType(@RequestParam(value = "typeOfBook") TypeOfBook typeOfBook,
                                                @RequestParam(value = "page", required = false) int page) {
        log.info("Inside the client controller, a method to get all books by type");
        return bookService.getAllBooksByType(typeOfBook, page - 1);
    }

    @Operation(summary = "Method get all books", description = "Allows to get all books from the database")
    @GetMapping("/books")
    public List<BookResponse> getAllBooks() {
        log.info("Inside Client controller get all books method");
        return bookService.getAllBooks();
    }

    @Operation(summary = "Method get by id", description = "Allows all users to get a book by ID")
    @GetMapping("/book/{id}")
    public BookResponse getBookById(@PathVariable Long id) {
        log.info("Inside Client controller get book by id method");
        return bookService.getBookById(id);
    }
}
