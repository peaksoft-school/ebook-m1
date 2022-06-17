package kg.peaksoft.ebookm1.services;

import kg.peaksoft.ebookm1.api.payloads.dto.enums.Genre;
import kg.peaksoft.ebookm1.api.payloads.dto.enums.RequestStatus;
import kg.peaksoft.ebookm1.api.payloads.dto.enums.TypeOfBook;
import kg.peaksoft.ebookm1.specifications.BookSpecification;
import kg.peaksoft.ebookm1.dataBase.entities.security.User;
import kg.peaksoft.ebookm1.api.payloads.dto.book.BookResponseView;
import kg.peaksoft.ebookm1.dataBase.mappers.book.BookEditMapper;
import kg.peaksoft.ebookm1.dataBase.mappers.book.BookViewMapper;
import kg.peaksoft.ebookm1.api.payloads.dto.book.BookRequest;
import kg.peaksoft.ebookm1.api.payloads.dto.book.BookResponse;
import kg.peaksoft.ebookm1.dataBase.entities.book.Book;
import kg.peaksoft.ebookm1.dataBase.repositories.BookRepository;
import kg.peaksoft.ebookm1.dataBase.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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

    public BookResponse updateBook(Long id, BookRequest request) {
        Book book = repository.findById(id).get();
        editMapper.updateBook(book, request);
        log.error("Failed book to update: {}", book.getTitle());
        return viewMapper.viewBook(repository.save(book));
    }

    public BookResponse getBookById(Long id) {
        Book book = repository.findById(id).get();
        log.info("Getting book by id: {}", book.getTitle());
        return viewMapper.viewBook(book);
    }

    public List<BookResponse> getAllBooks() {
        log.info("Getting books all: ");
        return viewMapper.viewBooks(repository.findAll());
    }

    public String countBooks(Long vendorId) {
        User vendor = vendorRepository.findById(vendorId).get();
        List<Book> booksOfVendor = new ArrayList<>();
        for (Book count: vendor.getBooks()) {
            booksOfVendor.add(count);
        }
        log.info("Vendor's book quantities: {}", booksOfVendor.size());
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

    public List<BookResponse> getAllSubmittedBooks(int page){
        int size = 10;
        Pageable pageable = PageRequest.of(page,size);
        log.info("Getting all the books sent to the application: ");
        return viewMapper.viewBooks(repository.findAllByStatus(RequestStatus.SUBMITTED,pageable));
    }

    public BookResponseView searchAndPagination(String name, int page) {
        int size=10;
        BookResponseView responseView = new BookResponseView();
        Pageable pageable = PageRequest.of(page, size);
        responseView.setBookResponses((viewMapper.viewBooks
                (viewMapper.searchBook(name, pageable))));
        log.info("Book search: ");
        return responseView;
    }

    public Page<Book> sortAndPagination(Integer pageNumber, Integer pageSize, String sortProperty) {
        Pageable pageable = null;
        if(null!=sortProperty){
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC,sortProperty);
        }else {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC,"name");
        }
        log.info("Book sort: ");
        return repository.findAll(pageable);
    }

    public List<BookResponse> filterByGenreAndTypeOfBooks(Genre genre, TypeOfBook typeOfBook, int page){
        int size=10;
        Specification<Book> filter = BookSpecification.getFilter(genre,typeOfBook);
        Pageable pageable = PageRequest.of(page, size);
        log.info("Sorting by genre and type: ");
        return viewMapper.viewBooks(repository.findAll(filter,pageable));
    }
}
