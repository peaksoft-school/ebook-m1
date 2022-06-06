package kg.peaksoft.ebookm1.services;

import kg.peaksoft.ebookm1.dataBase.entities.security.User;
import kg.peaksoft.ebookm1.dataBase.mappers.book.BookEditMapper;
import kg.peaksoft.ebookm1.dataBase.mappers.book.BookViewMapper;
import kg.peaksoft.ebookm1.api.payloads.dto.book.BookRequest;
import kg.peaksoft.ebookm1.api.payloads.dto.book.BookResponse;
import kg.peaksoft.ebookm1.dataBase.entities.book.Book;
import kg.peaksoft.ebookm1.dataBase.repositories.BookRepository;
import kg.peaksoft.ebookm1.dataBase.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
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

    public BookResponse createBook(BookRequest request) {
        Book book = editMapper.createBook(request);
        repository.save(book);
        return viewMapper.viewBook(book);
    }

    public BookResponse updateBook(Long id, BookRequest request) {
        Book book = repository.findById(id).get();
        editMapper.updateBook(book, request);
        return viewMapper.viewBook(repository.save(book));
    }

    public BookResponse getBookById(Long id) {
        Book book = repository.findById(id).get();
        return viewMapper.viewBook(book);
    }

    public BookResponse deleteBookById(Long id) {
        Book book = repository.findById(id).get();
        repository.deleteById(id);
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
}
