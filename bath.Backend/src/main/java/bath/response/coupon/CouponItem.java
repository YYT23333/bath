package bath.response.coupon;

import bath.entity.coupon.Coupon;
import bath.entity.groupon.Groupon;
import bath.response.groupon.GrouponItem;

import java.util.Date;

public class CouponItem {
    private String id;
    private String openid;
    private boolean isUsed;
    private GrouponItem groupon;
    private Date buyTime;
    private boolean isValid;
    private Date loseEffectiveTime;

    public CouponItem() {
    }
    public CouponItem(Coupon coupon){
        this.id=coupon.getId();
        this.openid=coupon.getUser().getOpenid();
        this.isUsed=coupon.isUsed();
        this.groupon=new GrouponItem(coupon.getGroupon());
        this.buyTime=coupon.getBuyTime();
        this.loseEffectiveTime=coupon.getLoseEffectiveTime();
        this.isValid=(coupon.getLoseEffectiveTime().before(new Date()));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public GrouponItem getGroupon() {
        return groupon;
    }

    public void setGroupon(GrouponItem groupon) {
        this.groupon = groupon;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public Date getLoseEffectiveTime() {
        return loseEffectiveTime;
    }

    public void setLoseEffectiveTime(Date loseEffectiveTime) {
        this.loseEffectiveTime = loseEffectiveTime;
    }
}
