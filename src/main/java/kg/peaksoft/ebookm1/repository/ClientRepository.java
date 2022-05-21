package kg.peaksoft.ebookm1.repository;

import kg.peaksoft.ebookm1.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Override
    <S extends Client> S save(S entity);
}