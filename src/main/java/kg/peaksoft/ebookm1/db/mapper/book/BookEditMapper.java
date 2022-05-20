package kg.peaksoft.ebookm1.db.mapper.book;

import kg.peaksoft.ebookm1.api.payLoad.dto.book.BookRequest;
import kg.peaksoft.ebookm1.db.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookEditMapper {

    public Book createBook(BookRequest request) {
        if (request == null) {
            return null;
        }
        Book book  = new Book();
        book.setTitle(request.getTitle());
        book.setBookType(request.getBookType());
        book.setAuthor(request.getAuthor());
        book.setPublishingHouse(request.getPublishingHouse());
        book.setAboutTheBook(request.getAboutTheBook());
        book.setBookFragment(request.getBookFragment());
        book.setLanguage(request.getLanguage());
        book.setYearOfIssue(request.getYearOfIssue());
        book.setPageVolume(request.getPageVolume());
        book.setPrice(request.getPrice());
        book.setNumberOfBooks(request.getNumberOfBooks());
        book.setDiscount(request.getDiscount());
        book.setBestseller(request.getBestseller());
        return book;
    }

    public Book updateBook(Book book, BookRequest request) {
        book.setTitle(request.getTitle());
        book.setBookType(request.getBookType());
        book.setAuthor(request.getAuthor());
        book.setPublishingHouse(request.getPublishingHouse());
        book.setAboutTheBook(request.getAboutTheBook());
        book.setBookFragment(request.getBookFragment());
        book.setLanguage(request.getLanguage());
        book.setYearOfIssue(request.getYearOfIssue());
        book.setPageVolume(request.getPageVolume());
        book.setPrice(request.getPrice());
        book.setNumberOfBooks(request.getNumberOfBooks());
        book.setDiscount(request.getDiscount());
        book.setBestseller(request.getBestseller());
        return book;
    }
}
