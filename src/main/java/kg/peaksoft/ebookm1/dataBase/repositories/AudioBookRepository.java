package kg.peaksoft.ebookm1.dataBase.repositories;

import kg.peaksoft.ebookm1.dataBase.entities.book.AudioBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudioBookRepository extends JpaRepository<AudioBook, Long> {
}