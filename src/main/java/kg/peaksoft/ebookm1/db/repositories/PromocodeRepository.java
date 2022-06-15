package kg.peaksoft.ebookm1.db.repositories;

import kg.peaksoft.ebookm1.db.entities.others.Promocode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromocodeRepository extends JpaRepository<Promocode, Long> {
}