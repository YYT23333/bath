package bath.response.coupon;

import bath.entity.coupon.Coupon;
import bath.response.Response;

public class CouponResponse extends Response{
    private CouponItem couponItem;

    public CouponResponse() {
    }

    public CouponResponse(Coupon coupon) {
        this.couponItem = new CouponItem(coupon);
    }

    public CouponItem getCouponItem() {
        return couponItem;
    }

    public void setCouponItem(CouponItem couponItem) {
        this.couponItem = couponItem;
    }
}
