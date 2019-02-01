package bath.dataservice.exchangeRecord;

import bath.entity.integral.ExchangeRecord;
import bath.entity.user.User;
import bath.exception.NotExistException;

import java.util.List;

public interface ExchangeRecordDataService {
    String add(ExchangeRecord exchangeRecord);
    ExchangeRecord findById(int id) throws NotExistException;
    List<ExchangeRecord> findAll();
    List<ExchangeRecord> findByUser(User user);
}
