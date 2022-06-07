package kg.peaksoft.ebookm1.dataBase.repositories;

import kg.peaksoft.ebookm1.api.payloads.dto.enums.RequestStatus;
import kg.peaksoft.ebookm1.dataBase.entities.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByStatus(RequestStatus requestStatus);
}