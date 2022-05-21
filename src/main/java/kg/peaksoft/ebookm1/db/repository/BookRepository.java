package kg.peaksoft.ebookm1.db.repository;

import kg.peaksoft.ebookm1.db.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
