package kg.peaksoft.ebookm1.mapper.book;

import kg.peaksoft.ebookm1.dto.book.BookResponse;
import kg.peaksoft.ebookm1.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookResponseMapper {

    public BookResponse viewBook(Book book){
        BookResponse response =  new BookResponse();
        response.setId(book.getId());
        response.setName(book.getBook());
        response.setUser(book.getUser());
        return response;
    }

    public List<BookResponse> bookResponseList(List<Book> bookList){
        List<BookResponse> responses = new ArrayList<>();

        for (Book bookMember:bookList) {
            responses.add(viewBook(bookMember));
        }
        return responses;
    }


}
