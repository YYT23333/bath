package bath.blservice.coupon;

import bath.entity.user.User;
import bath.exception.NotExistException;
import bath.response.InfoResponse;
import bath.response.coupon.CouponListResponse;
import bath.response.coupon.CouponResponse;

public interface CouponBlService {
    /**
     * 用户使用优惠码（跟新优惠码状态）
     * @param id
     * @return 是否成功
     */
    InfoResponse useCoupon(String id)throws NotExistException;
    /**
     *通过id查找优惠码
     * @return 优惠码
     */
    CouponResponse findCouponById(String id)throws NotExistException;

    /**
     * 通过用户的openid，查找用户的所有优惠券
     * @param openid
     * @return 该用户的所有优惠券
     */
    CouponListResponse findByUser(String openid) throws NotExistException;
}
