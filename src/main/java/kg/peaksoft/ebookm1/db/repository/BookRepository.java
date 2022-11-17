package kg.peaksoft.ebookm1.db.repository;

import kg.peaksoft.ebookm1.db.enums.RequestStatus;
import kg.peaksoft.ebookm1.db.entity.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE UPPER(b.author) LIKE CONCAT('%',:name,'%') " +
            "OR UPPER(b.publishingHouse) LIKE CONCAT('%', :name, '%') " +
            "OR UPPER(b.title) LIKE CONCAT('%', :name, '%') " +
            "OR UPPER(b.genreEnum) LIKE CONCAT('%', :name, '%') " +
            "OR UPPER(b.typeOfBook) LIKE CONCAT('%', :name, '%') " +
            "OR UPPER(b.bookLanguage) LIKE CONCAT('%', :name, '%') ")
    List<Book> searchAndPagination(@Param("name") String name, Pageable pageable);

    List<Book> findAllByStatus(RequestStatus requestStatus, Pageable pageable);

    List<Book> findAllByStatus(RequestStatus requestStatus);

    List<Book> findAll(Specification<Book> specification, Pageable pageable);

}