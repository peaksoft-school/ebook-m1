package kg.peaksoft.ebookm1.db.mapper.book;

import kg.peaksoft.ebookm1.api.payLoad.dto.book.BookRequest;
import kg.peaksoft.ebookm1.api.payLoad.dto.book.BookResponse;
import kg.peaksoft.ebookm1.db.entity.Book;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookViewMapper {

    public BookResponse viewBook(Book book) {
        if (book == null) {
            return null;
        }
        BookResponse response = new BookResponse();
        if (book.getId() != null) {
            response.setId(book.getId());
        }
        response.setTitle(book.getTitle());
        response.setBookType(book.getBookType());
        response.setAuthor(book.getAuthor());
        response.setPublishingHouse(book.getPublishingHouse());
        response.setAboutTheBook(book.getAboutTheBook());
        response.setBookFragment(book.getBookFragment());
        response.setLanguage(book.getLanguage());
        response.setYearOfIssue(book.getYearOfIssue());
        response.setPageVolume(book.getPageVolume());
        response.setPrice(book.getPrice());
        response.setNumberOfBooks(book.getNumberOfBooks());
        response.setDiscount(book.getDiscount());
        response.setBestseller(book.getBestseller());
        return response;
    }

    public List<BookResponse> viewBooks(List<Book> books) {
        List<BookResponse> responses = new ArrayList<>();
        for (Book book : books) {
            responses.add(viewBook(book));
        }
        return responses;
    }
}
