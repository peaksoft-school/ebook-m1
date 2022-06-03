package kg.peaksoft.ebookm1.dataBase.repositories;

import kg.peaksoft.ebookm1.dataBase.entities.others.Promocode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromocodeRepository extends JpaRepository<Promocode, Long> {
}