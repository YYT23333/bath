package bath.bl.exchange;

import bath.blservice.exchange.ExchangeBlService;
import bath.dataservice.coupon.CouponDataService;
import bath.dataservice.exchangeRecord.ExchangeRecordDataService;
import bath.dataservice.groupon.GrouponDataService;
import bath.dataservice.user.UserDataService;
import bath.entity.coupon.Coupon;
import bath.entity.groupon.Groupon;
import bath.entity.integral.ExchangeRecord;
import bath.entity.user.User;
import bath.exception.IntegralDeficiencyException;
import bath.exception.LowStocksException;
import bath.exception.NotExistException;
import bath.response.InfoResponse;
import bath.response.exchangeRecords.ExchangeRecordListResponse;
import bath.response.exchangeRecords.ExchangeRecordResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class ExchangeBlServiceImpl implements ExchangeBlService {
    private final ExchangeRecordDataService exchangeRecordDataService;
    private final UserDataService userDataService;
    private final GrouponDataService grouponDataService;
    private final CouponDataService couponDataService;
    @Autowired
    public ExchangeBlServiceImpl(ExchangeRecordDataService exchangeRecordDataService,UserDataService userDataService,GrouponDataService grouponDataService,CouponDataService couponDataService){
        this.exchangeRecordDataService=exchangeRecordDataService;
        this.userDataService=userDataService;
        this.grouponDataService=grouponDataService;
        this.couponDataService=couponDataService;
    }
    @Override
    public ExchangeRecordListResponse getAll() {
        return new ExchangeRecordListResponse(exchangeRecordDataService.findAll());
    }

    @Override
    public ExchangeRecordResponse findById(int id) throws NotExistException {
        return new ExchangeRecordResponse(exchangeRecordDataService.findById(id));
    }

    @Override
    @Transactional
    public InfoResponse exchange(String openid, String grouponId) throws NotExistException, IntegralDeficiencyException, LowStocksException {
        User user = userDataService.findByOpenid(openid);
        Groupon groupon = grouponDataService.findById(grouponId);
        if (user.getintegral() < groupon.getPrice()) {
            throw new IntegralDeficiencyException();
        }
        if (groupon.getAmount() < 1) {
            throw new LowStocksException();
        }
        user.setintegral((int) (user.getintegral() - groupon.getPrice()));
        groupon.setAmount(groupon.getAmount()-1);
        userDataService.update(user);
        grouponDataService.update(groupon);
        Coupon coupon = new Coupon(groupon,user);
        couponDataService.add(coupon);
        ExchangeRecord record=new ExchangeRecord();
        record.setGroupOn(groupon);
        record.setUser(user);
        record.setTime(new Date());
        exchangeRecordDataService.add(record);
        return new InfoResponse();
    }

    @Override
    public ExchangeRecordListResponse findByUser(String openid) throws NotExistException {
        User user=userDataService.findByOpenid(openid);
        return new ExchangeRecordListResponse(exchangeRecordDataService.findByUser(user));
    }
}
