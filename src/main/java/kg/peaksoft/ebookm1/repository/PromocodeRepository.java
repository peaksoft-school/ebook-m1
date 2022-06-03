package kg.peaksoft.ebookm1.repository;

import kg.peaksoft.ebookm1.entity.Promocode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromocodeRepository extends JpaRepository<Promocode, Long> {
}