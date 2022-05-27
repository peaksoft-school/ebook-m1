package kg.peaksoft.ebookm1.service;

import kg.peaksoft.ebookm1.book.BookEditMapper;
import kg.peaksoft.ebookm1.book.BookViewMapper;
import kg.peaksoft.ebookm1.dto.book.BookRequest;
import kg.peaksoft.ebookm1.dto.book.BookResponse;
import kg.peaksoft.ebookm1.entity.book.Book;
import kg.peaksoft.ebookm1.repository.BookRepository;
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

    public BookResponse getByIdBook(Long id) {
        Book book = repository.findById(id).get();
        return viewMapper.viewBook(book);
    }

    public BookResponse deleteByIdBook(Long id) {
        Book book = repository.findById(id).get();
        repository.deleteById(id);
        System.out.print("Successfully deleted: ");
        return viewMapper.viewBook(book);
    }

    public List<BookResponse> getAllBooks() {
        return viewMapper.viewBooks(repository.findAll());
    }
}
