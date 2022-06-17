package kg.peaksoft.ebookm1.db.mappers.book;

import kg.peaksoft.ebookm1.api.controllers.payloads.dto.book.BookRequest;
import kg.peaksoft.ebookm1.db.entities.book.*;
import org.springframework.stereotype.Component;

@Component
public class BookEditMapper {

    public Book createBook(BookRequest bookRequest) {
        if (bookRequest == null) {
            return null;
        }
        Book book = new Book();
        book.setImage(bookRequest.getImage());
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setPublishingHouse(bookRequest.getPublishingHouse());
        book.setAboutTheBook(bookRequest.getAboutTheBook());
        book.setBookFragment(bookRequest.getBookFragment());
        book.setBookLanguage(bookRequest.getBookLanguage());
        book.setYearOfIssue(bookRequest.getYearOfIssue());
        book.setPageVolume(bookRequest.getPageVolume());
        book.setPrice(bookRequest.getPrice());
        book.setAmountOfBooks(bookRequest.getAmountOfBooks());
        book.setDiscount(bookRequest.getDiscount());
        book.setBestseller(bookRequest.getBestseller());
        book.setGenreEnum(bookRequest.getGenreEnum());
        book.setTypeOfBook(bookRequest.getTypeOfBook());
        return book;
    }

    public Book updateBook(Book book, BookRequest bookRequest) {
        book.setImage(bookRequest.getImage());
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setPublishingHouse(bookRequest.getPublishingHouse());
        book.setAboutTheBook(bookRequest.getAboutTheBook());
        book.setBookFragment(bookRequest.getBookFragment());
        book.setBookLanguage(bookRequest.getBookLanguage());
        book.setYearOfIssue(bookRequest.getYearOfIssue());
        book.setPageVolume(bookRequest.getPageVolume());
        book.setPrice(bookRequest.getPrice());
        book.setAmountOfBooks(bookRequest.getAmountOfBooks());
        book.setDiscount(bookRequest.getDiscount());
        book.setBestseller(bookRequest.getBestseller());
        book.setStatus(bookRequest.getStatus());
        book.setComments(bookRequest.getComments());
        book.setGenreEnum(bookRequest.getGenreEnum());
        book.setTypeOfBook(bookRequest.getTypeOfBook());
        return book;
    }
}
