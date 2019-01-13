package bath.response.coupon;

import bath.entity.coupon.Coupon;
import bath.entity.groupon.Groupon;
import java.util.Date;

public class CouponItem {
    private String id;
    private boolean isUsed;
    private Groupon groupon;
    private Date buyTime;
    private boolean isValid;
    private Date loseEffectiveTime;

    public CouponItem() {
    }
    public CouponItem(Coupon coupon){
        this.id=coupon.getId();
        this.isUsed=coupon.isUsed();
        this.groupon=coupon.getGroupon();
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

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public Groupon getGroupon() {
        return groupon;
    }

    public void setGroupon(Groupon groupon) {
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
