package kg.peaksoft.ebookm1.db.repository;

import kg.peaksoft.ebookm1.db.entity.Promocode;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromocodeRepository extends JpaRepository<Promocode, Long> {

    Promocode findPromocodeByPromoName(String name);

    List<Promocode> findAll(Specification<Promocode> specification,Pageable pageable);
}