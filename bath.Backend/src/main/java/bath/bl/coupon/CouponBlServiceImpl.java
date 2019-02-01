package bath.bl.coupon;

import bath.blservice.coupon.CouponBlService;
import bath.dataservice.coupon.CouponDataService;
import bath.dataservice.user.UserDataService;
import bath.entity.coupon.Coupon;
import bath.entity.user.User;
import bath.exception.NotExistException;
import bath.response.InfoResponse;
import bath.response.coupon.CouponListResponse;
import bath.response.coupon.CouponResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CouponBlServiceImpl implements CouponBlService {
    private final CouponDataService couponDataService;
    private final UserDataService userDataService;
    @Autowired
    public CouponBlServiceImpl(CouponDataService couponDataService,UserDataService userDataService){
        this.couponDataService=couponDataService;
        this.userDataService=userDataService;
    }
    @Override
    public InfoResponse useCoupon(String id) throws NotExistException {
        Coupon coupon=couponDataService.findById(id);
        coupon.setUsed(true);
        coupon.setUseTime(new Date());
        couponDataService.update(coupon);
        return new InfoResponse();
    }

    @Override
    public CouponResponse findCouponById(String id) throws NotExistException {
        return new CouponResponse(couponDataService.findById(id));
    }

    @Override
    public CouponListResponse findByUser(String openid) throws NotExistException {
        User user=userDataService.findByOpenid(openid);
        return new CouponListResponse(couponDataService.findByUser(user));
    }
}
