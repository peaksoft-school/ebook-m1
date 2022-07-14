package kg.peaksoft.ebookm1.db.repository;

import kg.peaksoft.ebookm1.db.entity.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}