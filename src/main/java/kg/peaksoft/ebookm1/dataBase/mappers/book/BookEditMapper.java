package kg.peaksoft.ebookm1.dataBase.mappers.book;

import kg.peaksoft.ebookm1.api.payloads.dto.book.BookRequest;
import kg.peaksoft.ebookm1.dataBase.entities.book.*;
import org.springframework.stereotype.Component;

@Component
public class BookEditMapper {

    public Book createAudioBook(BookRequest bookRequest) {
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
        book.setGenere(bookRequest.getGenere());
        book.setTypeOfBook(bookRequest.getTypeOfBook());
        AudioBook audioBook = new AudioBook();
        audioBook.setAudioFragment(bookRequest.getAudioBookFragment());
        audioBook.setBook(book);
        book.setAudioBook(audioBook);
       return book;
    }

    public Book createEBook(BookRequest bookRequest) {
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
        book.setGenere(bookRequest.getGenere());
        book.setTypeOfBook(bookRequest.getTypeOfBook());

        EBook eBook = new EBook();
        eBook.setEBookFragment(bookRequest.getEBookFragment());
        eBook.setBook(book);
        book.setEBook(eBook);
        return book;
    }

    public Book createPaperBook(BookRequest bookRequest) {
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
        book.setGenere(bookRequest.getGenere());
        book.setTypeOfBook(bookRequest.getTypeOfBook());

        PaperBook paperBook = new PaperBook();
        paperBook.setPaperBookFragment(bookRequest.getPaperBookFragment());
        paperBook.setBook(book);
        book.setPaperBook(paperBook);
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
        book.setGenere(bookRequest.getGenere());
        book.setTypeOfBook(bookRequest.getTypeOfBook());
        return book;
    }
}
