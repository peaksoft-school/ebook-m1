package kg.peaksoft.ebookm1.repository;

import kg.peaksoft.ebookm1.entity.Book;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

//    @Modifying
//    @Query(value = "update Book b set b.promocode_id = ? where b.user_id= ?",nativeQuery = true)
//    Optional<Book> updateBookTableSetPromocodeId(Long promocode_id, Long user_id );



}