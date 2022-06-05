package kg.peaksoft.ebookm1.services;

import kg.peaksoft.ebookm1.api.payloads.dto.book.BookResponseView;
import kg.peaksoft.ebookm1.dataBase.mappers.book.BookEditMapper;
import kg.peaksoft.ebookm1.dataBase.mappers.book.BookViewMapper;
import kg.peaksoft.ebookm1.api.payloads.dto.book.BookRequest;
import kg.peaksoft.ebookm1.api.payloads.dto.book.BookResponse;
import kg.peaksoft.ebookm1.dataBase.entities.book.Book;
import kg.peaksoft.ebookm1.dataBase.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public BookResponseView searchAndPagination(String name, int page, int size) {
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
}
