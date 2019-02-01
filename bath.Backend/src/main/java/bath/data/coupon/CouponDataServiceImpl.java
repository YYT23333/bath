package bath.data.coupon;

import bath.data.dao.coupon.CouponDao;
import bath.dataservice.coupon.CouponDataService;
import bath.entity.coupon.Coupon;
import bath.entity.user.User;
import bath.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CouponDataServiceImpl implements CouponDataService {
    private final CouponDao couponDao;
    @Autowired
    public CouponDataServiceImpl(CouponDao couponDao){
        this.couponDao=couponDao;
    }
    @Override
    public String add(Coupon coupon) {
        return couponDao.save(coupon).getId();
    }

    @Override
    public void update(Coupon coupon) throws NotExistException {
        if(couponDao.findById(coupon.getId()+"").isPresent()){
            couponDao.save(coupon);
        }else{
            throw new NotExistException("Coupon ID",coupon.getId());
        }

    }

    @Override
    public Coupon findById(String id) throws NotExistException {
        Optional<Coupon> couponOptional=couponDao.findById(id);
        if(couponOptional.isPresent()){
            return couponOptional.get();
        }else{
            throw new NotExistException("Coupon ID",id);
        }
    }

    @Override
    public List<Coupon> findByUser(User user) {
        return couponDao.findByUser(user);

    }
}
