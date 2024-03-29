package kg.peaksoft.ebookm1.db.repository;

import kg.peaksoft.ebookm1.db.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {

}
