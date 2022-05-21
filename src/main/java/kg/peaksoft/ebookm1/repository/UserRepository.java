package kg.peaksoft.ebookm1.repository;

import kg.peaksoft.ebookm1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}