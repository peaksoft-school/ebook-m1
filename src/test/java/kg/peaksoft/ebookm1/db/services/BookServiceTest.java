package kg.peaksoft.ebookm1.db.services;

import kg.peaksoft.ebookm1.api.payload.dto.book.BookRequest;
import kg.peaksoft.ebookm1.api.payload.dto.book.BookResponse;
import kg.peaksoft.ebookm1.api.payload.dto.book.BookResponseView;
import kg.peaksoft.ebookm1.db.entity.Book;
import kg.peaksoft.ebookm1.db.entity.security.Role;
import kg.peaksoft.ebookm1.db.entity.security.User;
import kg.peaksoft.ebookm1.db.enums.BookLanguage;
import kg.peaksoft.ebookm1.db.mapper.BookViewMapper;
import kg.peaksoft.ebookm1.db.repository.BookRepository;
import kg.peaksoft.ebookm1.db.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static kg.peaksoft.ebookm1.db.enums.Genre.ACTION_ADVENTURE;
import static kg.peaksoft.ebookm1.db.enums.RequestStatus.APPROVED;
import static kg.peaksoft.ebookm1.db.enums.RequestStatus.SUBMITTED;
import static kg.peaksoft.ebookm1.db.enums.TypeOfBook.AUDIO_BOOK;
import static kg.peaksoft.ebookm1.db.enums.TypeOfBook.PAPER_BOOK;
import static kg.peaksoft.ebookm1.db.repository.specifications.BookSpecification.getByStatusAndTypeOfBook;
import static kg.peaksoft.ebookm1.db.repository.specifications.BookSpecification.getFilter;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository repository;
    @Mock
    private BookViewMapper viewMapper;
    @Mock
    private UserRepository vendorRepository;
    @InjectMocks
    private BookService bookService;

    private BookResponse bookResponse;
    private BookRequest bookRequest;
    private Book book1;
    private List<BookResponse> responseList = new ArrayList<>();
    private List<Book> bookList = new ArrayList<>();
    private User vendor;

    @BeforeEach
    void prepare() {
        var vendorRole = new Role();
        vendorRole.setName("ROLE_VENDOR");
        var clientRole = new Role();
        clientRole.setName("ROLE_CLIENT");

        book1 = Book.builder()
                .id(1L)
                .aboutTheBook("about the book1")
                .amountOfBooks(10)
                .author("Pushkin")
                .bestseller(true)
                .bookFragment("book fragment")
                .bookLanguage(BookLanguage.RUSSIAN)
                .discount(10)
                .title("Evgenii Onegin")
                .genreEnum(ACTION_ADVENTURE)
                .typeOfBook(PAPER_BOOK)
                .build();

        bookResponse = BookResponse.builder()
                .id(1L)
                .aboutTheBook("about the book1")
                .amountOfBooks(10)
                .author("Pushkin")
                .basketPrice(1.0)
                .bestseller(true)
                .bookFragment("book fragment")
                .bookLanguage(BookLanguage.RUSSIAN)
                .title("Evgenii Onegin")
                .comments("comment1")
                .day(10L)
                .discount(10)
                .genreEnum(ACTION_ADVENTURE)
                .typeOfBook(PAPER_BOOK)
                .build();

        bookRequest = BookRequest.builder()
                .aboutTheBook("about the book1")
                .amountOfBooks(10)
                .author("Pushkin")
                .basketPrice(1.0)
                .bestseller(true)
                .bookFragment("book fragment")
                .bookLanguage(BookLanguage.RUSSIAN)
                .title("Evgenii Onegin")
                .comments("comment1")
                .discount(10)
                .genreEnum(ACTION_ADVENTURE)
                .typeOfBook(PAPER_BOOK)
                .build();

        vendor = User.builder()
                .id(1L)
                .email("vendor@gmail.com")
                .password("1234567")
                .roles(List.of(vendorRole))
                .firstName("Vendor")
                .lastName("Vendor")
                .books(List.of(book1))
                .phoneNumber("+797979797")
                .build();

    }

    @Test
    void updateBook() {
    }

    @Test
    @DisplayName("update status of book if book existed")
    void shouldReturnUpdatedStatusOrCommentOfBookIfExisted() {
        doReturn(Optional.of(book1)).when(repository).findById(1L);
        doReturn(book1).when(repository).save(book1);
        bookResponse.setStatus(bookRequest.getStatus());
        bookResponse.setComments(bookRequest.getComments());
        doReturn(bookResponse).when(viewMapper).viewBook(book1);
        assertThat(bookService.updateRequestStatus(1L, bookRequest)).isEqualTo(bookResponse);
    }


    @Test
    @DisplayName("Test for get book by teacherId")
    void shouldReturnBookExistedById() {
        doReturn(Optional.of(book1)).when(repository).findById(1L);
        doReturn(bookResponse).when(viewMapper).viewBook(book1);
        assertThat(bookService.getBookById(1L)).isEqualTo(bookResponse);
    }

    @Test
    @DisplayName("Test for get all books")
    void shouldReturnListOfAllBooksExisted() {
        responseList.add(bookResponse);
        bookList.add(book1);
        doReturn(bookList).when(repository).findAll();
        doReturn(responseList).when(viewMapper).viewBooks(bookList);
        assertThat(bookService.getAllBooks().size()).isEqualTo(responseList.size());
    }

    @Test
    @DisplayName("Test for count books of vendor")
    void countBooks() {
        List<Book> booksOfVendor = new ArrayList<>();
        booksOfVendor.add(book1);
        doReturn(Optional.of(vendor)).when(vendorRepository).findById(1L);
        String s = vendor + " book quantity: " + booksOfVendor.size();
        assertThat(bookService.countBooks(1L)).isEqualTo(s);
    }

    @Test
    @DisplayName("Test for get all books of vendor")
    void getAllVendorBooks() {
        List<BookResponse> responses = new ArrayList<>();
        responses.add(bookResponse);
        doReturn(Optional.of(vendor)).when(vendorRepository).findById(1L);
        doReturn(bookResponse).when(viewMapper).viewBook(book1);
        assertThat(bookService.getAllVendorBooks(1L).size()).isEqualTo(responses.size());
    }

    @Test
    @DisplayName("Test for get SUBMITTED books of vendor")
    void getAllSubmittedBooks() {
        book1.setStatus(SUBMITTED);
        bookList.add(book1);
        bookResponse.setStatus(SUBMITTED);
        responseList.add(bookResponse);
        int size = 10;
        Pageable pageable = PageRequest.of(1, size);
        doReturn(bookList).when(repository).findAllByStatus(SUBMITTED, pageable);
        doReturn(responseList).when(viewMapper).viewBooks(bookList);
        assertThat((bookService.getAllSubmittedBooks(1)).size()).isEqualTo(responseList.size());
    }

    @Test
    @DisplayName("Test for get APPROVED books of vendor")
    void getAllApprovedBooks() {
        int size = 10;
        book1.setStatus(APPROVED);
        bookList.add(book1);
        bookResponse.setStatus(APPROVED);
        responseList.add(bookResponse);
        Pageable pageable = PageRequest.of(1, size);
        doReturn(bookList).when(repository).findAllByStatus(APPROVED, pageable);
        doReturn(responseList).when(viewMapper).viewBooks(bookList);
        assertThat((bookService.getAllApprovedBooks(1))).isEqualTo(responseList);
        assertThat((bookService.getAllApprovedBooks(1))).hasSize(1);
    }

    @Test
    @DisplayName("\"test for filter get all approved by genre and type book if existed\"")
    void getAllApprovedBookByGenreAndType() {
        int size = 2;
        bookList.add(book1);
        bookResponse.setStatus(APPROVED);
        responseList.add(bookResponse);
        Pageable pageable1 = PageRequest.of(10, size);
        Specification<Book> specification1 = getByStatusAndTypeOfBook(ACTION_ADVENTURE, PAPER_BOOK, APPROVED);
        when(viewMapper.viewBooks(repository.findAll(specification1, pageable1))).thenReturn(responseList);
        assertThat(bookService.getAllApprovedBookByGenreAndType(ACTION_ADVENTURE, AUDIO_BOOK, 10)).isEqualTo(responseList);
    }

    @Test
    @DisplayName("Test for search and pagination")
    void searchAndPagination() {
        responseList.add(bookResponse);
        int size = 10;
        String name = "Pushkin";
        BookResponseView responseView = new BookResponseView();
        responseView.setBookResponses(responseList);
        Pageable pageable = PageRequest.of(1, size);
        doReturn(bookList).when(viewMapper).searchBook(name, pageable);
        doReturn(responseList).when(viewMapper).viewBooks(bookList);


        var actualityBookResponseView = bookService.searchAndPagination(name, 1);
        assertThat(actualityBookResponseView.getBookResponses()).isEqualTo(responseView.getBookResponses());
        assertThat(actualityBookResponseView.getBookResponses().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Test for sort and pagination")
    void sortAndPagination() {
        bookList.add(book1);
        String sortProperty = "sortProperty";
        Page<Book> all1 = new PageImpl<>(bookList);
        if (null != sortProperty) {
            doReturn(all1).when(repository).findAll(PageRequest.of(1, 1, Sort.Direction.ASC, sortProperty));
        } else {
            doReturn(all1).when(repository).findAll(PageRequest.of(1, 1, Sort.Direction.ASC, "sortProperty"));
        }
        assertThat((bookService.sortAndPagination(1, 1, "sortProperty")).getSize())
                .isEqualTo(1);
    }

    @Test
    @DisplayName("test for filter by genre and type book if existed")
    void filterByGenreAndTypeOfBooks() {
        int size = 2;
        bookList.add(book1);
        bookResponse.setStatus(APPROVED);
        responseList.add(bookResponse);
        Pageable pageable1 = PageRequest.of(10, size);
        Specification<Book> specification1 = getFilter(ACTION_ADVENTURE, PAPER_BOOK);
        when(viewMapper.viewBooks(repository.findAll(specification1, pageable1))).thenReturn(responseList);
        assertThat(bookService.filterByGenreAndTypeOfBooks(ACTION_ADVENTURE, AUDIO_BOOK, 10)).isEqualTo(responseList);
    }
}