package bath.dataservice.coupon;

import bath.entity.coupon.Coupon;
import bath.entity.user.User;
import bath.exception.NotExistException;

import java.util.List;

public interface CouponDataService {
    String add(Coupon coupon);
    void update(Coupon coupon) throws NotExistException;
    Coupon findById(String id) throws NotExistException;
    List<Coupon> findByUser(User user);
}
