package kg.peaksoft.ebookm1.db.repositories;

import kg.peaksoft.ebookm1.db.entities.others.HistoryOperation;
import kg.peaksoft.ebookm1.db.entities.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryOperationRepository extends JpaRepository<HistoryOperation, Long> {

    HistoryOperation findByUser(User user);
}