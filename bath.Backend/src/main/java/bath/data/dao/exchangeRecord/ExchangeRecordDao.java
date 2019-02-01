package bath.data.dao.exchangeRecord;

import bath.entity.integral.ExchangeRecord;
import bath.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExchangeRecordDao extends JpaRepository<ExchangeRecord, String> {
    List<ExchangeRecord> findByUser(User user);
    Optional<ExchangeRecord> findById(int id);
}
