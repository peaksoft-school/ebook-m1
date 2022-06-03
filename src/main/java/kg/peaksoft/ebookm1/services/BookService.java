package kg.peaksoft.ebookm1.services;

import kg.peaksoft.ebookm1.dataBase.mappers.book.BookEditMapper;
import kg.peaksoft.ebookm1.dataBase.mappers.book.BookViewMapper;
import kg.peaksoft.ebookm1.api.payloads.dto.book.BookRequest;
import kg.peaksoft.ebookm1.api.payloads.dto.book.BookResponse;
import kg.peaksoft.ebookm1.dataBase.entities.book.Book;
import kg.peaksoft.ebookm1.dataBase.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final BookEditMapper editMapper;
    private final BookViewMapper viewMapper;

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
        System.out.print("Successfully deleted: ");
        return viewMapper.viewBook(book);
    }

    public List<BookResponse> getAllBooks() {
        return viewMapper.viewBooks(repository.findAll());
    }
}
