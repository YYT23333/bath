package bath.response.coupon;

import bath.entity.coupon.Coupon;
import bath.response.Response;

import java.util.ArrayList;
import java.util.List;

public class CouponListResponse extends Response {
    private List<CouponItem> couponItemList;

    public CouponListResponse(List<Coupon> couponList) {
        this.couponItemList=new ArrayList<>();
        if(couponList!=null && couponList.size()>0){
            for(Coupon temp:couponList){
                this.couponItemList.add(new CouponItem(temp));
            }
        }
    }

    public List<CouponItem> getCouponItemList() {
        return couponItemList;
    }

    public void setCouponItemList(List<CouponItem> couponItemList) {
        this.couponItemList = couponItemList;
    }
}
