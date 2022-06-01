package kg.peaksoft.ebookm1.dataBase.repository;

import kg.peaksoft.ebookm1.dataBase.entity.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}