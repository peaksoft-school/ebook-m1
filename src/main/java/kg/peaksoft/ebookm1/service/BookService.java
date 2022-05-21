package kg.peaksoft.ebookm1.service;

import kg.peaksoft.ebookm1.api.payLoad.dto.book.BookRequest;
import kg.peaksoft.ebookm1.api.payLoad.dto.book.BookResponse;
import kg.peaksoft.ebookm1.db.entity.Book;
import kg.peaksoft.ebookm1.db.mapper.book.BookEditMapper;
import kg.peaksoft.ebookm1.db.mapper.book.BookViewMapper;
import kg.peaksoft.ebookm1.db.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final BookEditMapper editMapper;
    private final BookViewMapper viewMapper;

    public BookResponse create(BookRequest request) {
        Book book = editMapper.createBook(request);
        repository.save(book);
        return viewMapper.viewBook(book);
    }

    public BookResponse update(Long id, BookRequest request) {
        Book book = repository.findById(id).get();
        editMapper.updateBook(book, request);
        return viewMapper.viewBook(repository.save(book));
    }

    public BookResponse getById(Long id) {
        Book book = repository.findById(id).get();
        return viewMapper.viewBook(book);
    }

    public BookResponse deleteById(Long id) {
        Book book = repository.findById(id).get();
        repository.deleteById(id);
        return viewMapper.viewBook(book);
    }

    public List<BookResponse> getAll() {
        return viewMapper.viewBooks(repository.findAll());
    }
}
