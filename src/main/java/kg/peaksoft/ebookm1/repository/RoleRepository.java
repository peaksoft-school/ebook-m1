package kg.peaksoft.ebookm1.repository;

import kg.peaksoft.ebookm1.entity.Role;
import kg.peaksoft.ebookm1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

//    @Query("select v from Role v where v.id = 2")
//    List<Role> findById(@PathVariable Long id, User user);
}