package kg.peaksoft.ebookm1.db.repository;

import kg.peaksoft.ebookm1.db.entity.Basket;
import kg.peaksoft.ebookm1.db.enums.PurchaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {

    List<Basket> findAllByStatus(PurchaseStatus purchaseStatus);

}
