package kg.peaksoft.ebookm1.repository;

import kg.peaksoft.ebookm1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}