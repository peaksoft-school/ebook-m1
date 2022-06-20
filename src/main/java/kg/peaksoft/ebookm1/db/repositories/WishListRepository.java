package kg.peaksoft.ebookm1.db.repositories;

import kg.peaksoft.ebookm1.db.entities.others.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
}
