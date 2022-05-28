package kg.peaksoft.ebookm1.book;

import kg.peaksoft.ebookm1.dto.book.BookResponse;
import kg.peaksoft.ebookm1.entity.book.Book;
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
        response.setImage(book.getImage());
        response.setTitle(book.getTitle());
        response.setAuthor(book.getAuthor());
        response.setPublishingHouse(book.getPublishingHouse());
        response.setAboutTheBook(book.getAboutTheBook());
        response.setBookFragment(book.getBookFragment());
        response.setBookLanguage(book.getBookLanguage());
        response.setYearOfIssue(book.getYearOfIssue());
        response.setPageVolume(book.getPageVolume());
        response.setPrice(book.getPrice());
        response.setAmountOfBooks(book.getAmountOfBooks());
        response.setDiscount(book.getDiscount());
        response.setBestseller(book.getBestseller());

        response.setEBook(book.getEBook());
        response.setPaperBook(book.getPaperBook());
        response.setAudioBook(book.getAudioBook());
        response.setGenre(book.getGenre());
        response.setPromo(book.getPromo());
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
