package kg.peaksoft.ebookm1.db.services;

import kg.peaksoft.ebookm1.db.entities.others.Promocode;
import kg.peaksoft.ebookm1.db.enums.Genre;
import kg.peaksoft.ebookm1.db.enums.RequestStatus;
import kg.peaksoft.ebookm1.db.enums.TypeOfBook;
import kg.peaksoft.ebookm1.db.repositories.PromocodeRepository;
import kg.peaksoft.ebookm1.db.repositories.specifications.BookSpecification;
import kg.peaksoft.ebookm1.db.entities.security.User;
import kg.peaksoft.ebookm1.api.controllers.payloads.dto.book.BookResponseView;
import kg.peaksoft.ebookm1.db.mappers.book.BookEditMapper;
import kg.peaksoft.ebookm1.db.mappers.book.BookViewMapper;
import kg.peaksoft.ebookm1.api.controllers.payloads.dto.book.BookRequest;
import kg.peaksoft.ebookm1.api.controllers.payloads.dto.book.BookResponse;
import kg.peaksoft.ebookm1.db.entities.book.Book;
import kg.peaksoft.ebookm1.db.repositories.BookRepository;
import kg.peaksoft.ebookm1.db.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final BookEditMapper editMapper;
    private final BookViewMapper viewMapper;
    private final UserRepository vendorRepository;
    private final PromocodeRepository promocodeRepository;

    public BookResponse updateBook(Long bookId, BookRequest request) {
        Book book = repository.findById(bookId).get();
        editMapper.updateBook(book, request);
        log.info("Successfully updated the book by id: {}", book.getId() + " - book id");
        return viewMapper.viewBook(repository.save(book));
    }
    public BookResponse updateRequestStatus(Long bookId, BookRequest request) {
        Book book = repository.findById(bookId).get();
        book.setStatus(request.getStatus());
        book.setComments(request.getComments());
        log.info("Successfully updated requested book status to: {}", book.getStatus());
        return viewMapper.viewBook(repository.save(book));
    }

    public BookResponse getBookById(Long id) {
        Book book = repository.findById(id).get();
        log.info("Getting book by id: {}", id + " - book id");
        return viewMapper.viewBook(book);
    }

    public List<BookResponse> getAllBooks() {
        log.info("Getting all books: ");
        return viewMapper.viewBooks(repository.findAll());
    }

    public String countBooks(Long vendorId) {
        User vendor = vendorRepository.findById(vendorId).get();
        List<Book> booksOfVendor = new ArrayList<>();
        for (Book count : vendor.getBooks()) {
            booksOfVendor.add(count);
        }
        log.info("Vendor's book quantities: {}", booksOfVendor.size() + ": count books");
        return vendor + " book quantity: " + booksOfVendor.size();
    }

    public List<BookResponse> getAllVendorBooks(Long vendorId) {
        List<BookResponse> responses = new ArrayList<>();
        User vendor = vendorRepository.findById(vendorId).get();
        for (Book book : vendor.getBooks()) {
            responses.add(viewMapper.viewBook(book));
        }
        log.info("Getting all the vendor's books: {}", responses);
        return responses;
    }

    public List<BookResponse> getAllSubmittedBooks(int page) {
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        log.info("Getting all the books from the submitted application: ");
        return viewMapper.viewBooks(repository.findAllByStatus(RequestStatus.SUBMITTED, pageable));
    }

    public List<BookResponse> getAllApprovedBooks(int page) {
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        log.info("Getting books by type: ");
        return viewMapper.viewBooks(repository.findAllByStatus(RequestStatus.APPROVED, pageable));
    }

    public List<BookResponse> getAllApprovedBookByGenreAndType(Genre genreEnum, TypeOfBook typeOfBook, int page) {
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        Specification<Book> filter = BookSpecification.getByStatusAndTypeOfBook(genreEnum, typeOfBook, RequestStatus.APPROVED);
        log.info("Method for filtering all books by genre and type: ");
        return viewMapper.viewBooks(repository.findAll(filter, pageable));
    }

    public BookResponseView searchAndPagination(String name, Integer page) {
        int size = 10;
        BookResponseView responseView = new BookResponseView();
        Pageable pageable = PageRequest.of(page, size);
        responseView.setBookResponses((viewMapper.viewBooks
                (viewMapper.searchBook(name, pageable))));
        log.info("Book search: ");
        return responseView;
    }

    public Page<Book> sortAndPagination(Integer pageNumber, Integer pageSize, String sortProperty) {
        Pageable pageable = null;
        if (null != sortProperty) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sortProperty);
        } else {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "sortProperty");
        }
        log.info("Book sort: ");
        return repository.findAll(pageable);
    }

    public List<BookResponse> filterByGenreAndTypeOfBooks(Genre genreEnum, TypeOfBook typeOfBook, int page) {
        int size = 10;
        Specification<Book> filter = BookSpecification.getFilter(genreEnum, typeOfBook);
        Pageable pageable = PageRequest.of(page, size);
        log.info("Sorting by genre and type: ");
        return viewMapper.viewBooks(repository.findAll(filter, pageable));
    }
}
