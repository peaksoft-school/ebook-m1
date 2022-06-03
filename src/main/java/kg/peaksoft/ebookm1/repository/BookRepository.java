package kg.peaksoft.ebookm1.repository;

import kg.peaksoft.ebookm1.entity.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}