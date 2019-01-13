package bath.entity.coupon;

import bath.entity.groupon.Groupon;
import bath.entity.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name="isUsed")
    private boolean isUsed;

    @Column(name="groupon")
    private Groupon groupon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="buyTime")
    private Date buyTime;

    @Column(name="loseEffectTime")
    private Date loseEffectiveTime;

    @Column(name="useTime")
    private Date useTime;

    public Coupon(Groupon groupon, User user) {
        this.groupon = groupon;
        this.user = user;
        this.isUsed=false;
        this.buyTime=new Date();
        this.loseEffectiveTime=groupon.getLoseEffectTime();
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

    public Date getLoseEffectiveTime() {
        return loseEffectiveTime;
    }

    public void setLoseEffectiveTime(Date loseEffectiveTime) {
        this.loseEffectiveTime = loseEffectiveTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }
}
