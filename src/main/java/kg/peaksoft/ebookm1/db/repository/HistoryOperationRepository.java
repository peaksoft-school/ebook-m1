package kg.peaksoft.ebookm1.db.repository;

import kg.peaksoft.ebookm1.db.entity.HistoryOperation;
import kg.peaksoft.ebookm1.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryOperationRepository extends JpaRepository<HistoryOperation, Long> {

    HistoryOperation findByUser(User user);
}