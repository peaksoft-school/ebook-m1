package kg.peaksoft.ebookm1.mapper.book;


import kg.peaksoft.ebookm1.dto.book.BookRequest;
import kg.peaksoft.ebookm1.dto.book.BookResponse;
import kg.peaksoft.ebookm1.dto.customer.CustomerRequest;
import kg.peaksoft.ebookm1.entity.Book;
import kg.peaksoft.ebookm1.entity.User;
import kg.peaksoft.ebookm1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookEditMapper {

    private final UserRepository repository;

    public Book createNewBook(BookRequest bookRequest){
        Book book = new Book();
        book.setBook(bookRequest.getName());
        return book;
    }
    public Book updateBook(Book book, BookRequest bookRequest){
        book.setBook(bookRequest.getName());
        return book;
    }
}
