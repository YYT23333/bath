package bath.data.exchangeRecord;

import bath.data.dao.exchangeRecord.ExchangeRecordDao;
import bath.dataservice.exchangeRecord.ExchangeRecordDataService;
import bath.entity.integral.ExchangeRecord;
import bath.entity.user.User;
import bath.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExchangeRecordDataServiceImpl implements ExchangeRecordDataService {
    private final ExchangeRecordDao exchangeRecordDao;
    @Autowired
    public ExchangeRecordDataServiceImpl(ExchangeRecordDao exchangeRecordDao){
        this.exchangeRecordDao=exchangeRecordDao;
    }
    @Override
    public String add(ExchangeRecord exchangeRecord) {
        return exchangeRecordDao.save(exchangeRecord).getId()+"";
    }

    @Override
    public ExchangeRecord findById(int id) throws NotExistException {
        Optional<ExchangeRecord> optional=exchangeRecordDao.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }else{
            throw new NotExistException("ExchangeRecord ID",id+"");
        }
    }

    @Override
    public List<ExchangeRecord> findAll() {
        return exchangeRecordDao.findAll();
    }

    @Override
    public List<ExchangeRecord> findByUser(User user) {
        return exchangeRecordDao.findByUser(user);
    }

}
