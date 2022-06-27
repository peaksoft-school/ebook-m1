package kg.peaksoft.ebookm1.db.repositories;

import kg.peaksoft.ebookm1.db.entities.others.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}