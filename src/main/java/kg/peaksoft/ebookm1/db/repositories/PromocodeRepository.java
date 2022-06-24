package kg.peaksoft.ebookm1.db.repositories;

import kg.peaksoft.ebookm1.db.entities.book.Book;
import kg.peaksoft.ebookm1.db.entities.others.Promocode;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromocodeRepository extends JpaRepository<Promocode, Long> {

    List<Promocode> findAllByPromoName(String name,Pageable pageable);
    List<Promocode> findAll(Specification<Promocode> specification,Pageable pageable);
}