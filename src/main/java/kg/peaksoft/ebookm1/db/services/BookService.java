package kg.peaksoft.ebookm1.db.services;

import kg.peaksoft.ebookm1.db.enums.Genre;
import kg.peaksoft.ebookm1.db.enums.RequestStatus;
import kg.peaksoft.ebookm1.db.enums.TypeOfBook;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final BookEditMapper editMapper;
    private final BookViewMapper viewMapper;
    private final UserRepository vendorRepository;

    public BookResponse updateBook(Long bookId, BookRequest request) {
        Book book = repository.findById(bookId).get();
        editMapper.updateBook(book, request);
        return viewMapper.viewBook(repository.save(book));
    }
    public BookResponse updateRequestStatus(Long bookId, BookRequest request) {
        Book book = repository.findById(bookId).get();
        book.setStatus(request.getStatus());
        book.setComments(request.getComments());
        return viewMapper.viewBook(repository.save(book));
    }

    public BookResponse getBookById(Long id) {
        Book book = repository.findById(id).get();
        return viewMapper.viewBook(book);
    }

    public List<BookResponse> getAllBooks() {
        return viewMapper.viewBooks(repository.findAll());
    }

    public String countBooks(Long vendorId) {
        User vendor = vendorRepository.findById(vendorId).get();
        List<Book> booksOfVendor = new ArrayList<>();
        for (Book count: vendor.getBooks()) {
            booksOfVendor.add(count);
        }
        return vendor + " book quantity: " + booksOfVendor.size();
    }

    public List<BookResponse> getAllVendorBooks(Long vendorId) {
            List<BookResponse> responses = new ArrayList<>();
            User vendor = vendorRepository.findById(vendorId).get();
        for (Book book : vendor.getBooks()) {
            responses.add(viewMapper.viewBook(book));
        }
        return responses;
    }

    public List<BookResponse> getAllSubmittedBooks(int page){
        int size = 10;
        Pageable pageable = PageRequest.of(page,size);
        return viewMapper.viewBooks(repository.findAllByStatus(RequestStatus.SUBMITTED,pageable));
    }

    public List<BookResponse> getAllApprovedBooks(int page){
        int size = 10;
        Pageable pageable = PageRequest.of(page,size);
        return viewMapper.viewBooks(repository.findAllByStatus(RequestStatus.APPROVED,pageable));
    }

    public List<BookResponse> getAllApprovedBookByGenreAndType(Genre genreEnum,TypeOfBook typeOfBook,int page,String sortProperty){
        int size = 10;
        Pageable pageable = null;
        Specification<Book> filter = BookSpecification.getByStatusAndTypeOfBook(genreEnum,typeOfBook,RequestStatus.APPROVED);

        if(null!=sortProperty){
            pageable = PageRequest.of(page, size, Sort.Direction.ASC,sortProperty);
        }else {
            pageable = PageRequest.of(page, size, Sort.Direction.ASC,"sort");
        }
        return viewMapper.viewBooks(repository.findAll(filter,pageable));
    }

    public BookResponseView searchAndPagination(String name, Integer page) {
        int size=10;
        BookResponseView responseView = new BookResponseView();
        Pageable pageable = PageRequest.of(page, size);
        responseView.setBookResponses((viewMapper.viewBooks
                (viewMapper.searchBook(name, pageable))));
        return responseView;
    }

    public Page<Book> sortAndPagination(Integer pageNumber, Integer pageSize, String sortProperty) {
        Pageable pageable = null;
        if(null!=sortProperty){
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC,sortProperty);
        }else {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC,"name");
        }
        return repository.findAll(pageable);
    }

    public List<BookResponse> filterByGenreAndTypeOfBooks(Genre genreEnum, TypeOfBook typeOfBook, int page){
        int size=10;
        Specification<Book> filter = BookSpecification.getFilter(genreEnum,typeOfBook);
        Pageable pageable = PageRequest.of(page, size);
        return viewMapper.viewBooks(repository.findAll(filter,pageable));
    }
}
