package kg.peaksoft.ebookm1.db.repository;

import kg.peaksoft.ebookm1.db.entity.securityEntity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}