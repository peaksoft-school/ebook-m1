package kg.peaksoft.ebookm1.service;

import kg.peaksoft.ebookm1.dto.book.BookRequest;
import kg.peaksoft.ebookm1.dto.book.BookResponse;
import kg.peaksoft.ebookm1.entity.Book;
import kg.peaksoft.ebookm1.mapper.book.BookEditMapper;
import kg.peaksoft.ebookm1.mapper.book.BookResponseMapper;
import kg.peaksoft.ebookm1.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookEditMapper editMapper;
    private final BookResponseMapper responseMapper;
    private final BookRepository bookRepository;

    public BookResponse createBook(BookRequest request){
        Book book = editMapper.createNewBook(request);
        bookRepository.save(book);
        return responseMapper.viewBook(book);
    }
    public BookResponse update(BookRequest request, Long id){
        Book book = bookRepository.findById(id).get();
        editMapper.updateBook(book,request);
        bookRepository.save(book);
        return responseMapper.viewBook(book);
    }
    public  BookResponse getBookById(long id){
        Book book = bookRepository.findById(id).get();
        return responseMapper.viewBook(book);
    }
}
