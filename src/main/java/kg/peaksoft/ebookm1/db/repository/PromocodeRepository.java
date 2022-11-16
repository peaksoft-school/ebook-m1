package kg.peaksoft.ebookm1.db.repository;

import kg.peaksoft.ebookm1.db.entity.PromoCode;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromocodeRepository extends JpaRepository<PromoCode, Long> {

    PromoCode findPromocodeByPromoName(String name);

    List<PromoCode> findAll(Specification<PromoCode> specification, Pageable pageable);
}