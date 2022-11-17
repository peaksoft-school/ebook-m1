package kg.peaksoft.ebookm1.db.repository;

import kg.peaksoft.ebookm1.db.entity.PromoCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoCodeRepository extends JpaRepository<PromoCode, Long> {

    PromoCode findPromoCodeByPromoName(String name);

}