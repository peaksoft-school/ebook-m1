package kg.peaksoft.ebookm1.dataBase.repositories;

import kg.peaksoft.ebookm1.dataBase.entities.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}