package kg.peaksoft.ebookm1.repository;

import kg.peaksoft.ebookm1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

//    @Query("select v from User v where v.id = 2")
//    Optional<User> findById(Long id);
}