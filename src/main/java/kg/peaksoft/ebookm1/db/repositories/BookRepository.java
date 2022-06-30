package kg.peaksoft.ebookm1.db.repositories;

import kg.peaksoft.ebookm1.db.enums.RequestStatus;
import kg.peaksoft.ebookm1.db.enums.TypeOfBook;
import kg.peaksoft.ebookm1.db.entities.book.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b from Book b where upper(b.author) like concat('%',:name,'%') " +
            "or upper(b.publishingHouse) like concat('%', :name, '%') " +
            "or upper(b.title) like concat('%', :name, '%') " +
            "or upper(b.genreEnum)like concat('%', :name, '%') "+
            "or upper(b.typeOfBook)like concat('%', :name, '%') "+
            "or upper(b.bookLanguage) like concat('%', :name, '%')")
    List<Book> searchAndPagination(@Param("name") String name, Pageable pageable);

    List<Book> findAllByStatus(RequestStatus requestStatus,Pageable pageable);
    List<Book> findAllByStatus(RequestStatus requestStatus);

    List<Book> findAll(Specification<Book> specification,Pageable pageable);

    List<Book> findBooksByTypeOfBook(TypeOfBook typeOfBook,Pageable pageable);



}