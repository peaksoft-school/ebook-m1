package kg.peaksoft.ebookm1.dataBase.repositories;

import kg.peaksoft.ebookm1.dataBase.entities.book.PaperBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperBookRepository extends JpaRepository<PaperBook, Long> {
}