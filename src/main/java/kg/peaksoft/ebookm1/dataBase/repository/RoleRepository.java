package kg.peaksoft.ebookm1.dataBase.repository;

import kg.peaksoft.ebookm1.dataBase.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}