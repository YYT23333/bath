package bath.entity.order;

import bath.entity.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bathOrder")
public class Order {

    @Id
    @Column(name = "id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //订单总价
    @Column(name = "total")
    private double total;


//    //积分优惠
//    @Column(name="integration")
//    private double integration;
//
//    //抵用券/优惠券 抵用金额
//    @Column(name="coupon")
//    private double coupon;
//
//    //实际付款
//    @Column(name="actualCost")
//    private double actualCost;

    @Column(name = "date")
    private Date date;

    @Column(name = "orderItems")
    @ElementCollection(targetClass = OrderGrouponItem.class)
    private List<OrderGrouponItem> orderItems;

    /** 支付状态
     * "init": 收到前端小程序支付请求，已生成订单
     * "stopped"：由于内部错误导致下单过程终止（在waiting前）
     * "waiting"：已回复给前端，前端将弹出微信窗口让用户支付确认，等待后台到账（尚未收到微信后台的支付确认）
     * "finished"：用户已经支付成功（已经收到微信后台的支付确认）
     * "failed"：用户支付失败（微信后台消息为支付失败）
     * "cancelled"：init和waiting状态超时，视为用户取消
     */
    @Column(name = "orderState")
    private String orderState;


    @Column(name="createTimeStamp")
    private String createTimeStamp; //创建时间戳

    @Column(name="waitingTimeStamp")
    private String waitingTimeStamp; //回复给微信小程序的时间戳

    @Column(name="finalTimeStamp")
    private String finalTimeStamp; //最终时间戳，收到微信后台支付结果时的时间戳
    public Order() {
    }

    public Order(User user, String id, List<OrderGrouponItem> orderItems, double total/*,double integration,double coupon,double avctualCost*/) {
        this.user=user;
        this.id = id;
        this.total=total;
//        this.integration=integration;
//        this.coupon=coupon;
//        this.actualCost=avctualCost;
        this.orderItems=orderItems;
        this.date=new Date();
        this.orderState = "init";
        this.createTimeStamp = String.valueOf(System.currentTimeMillis());
    }


    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTimeStamp() {
        return createTimeStamp;
    }

    public void setCreateTimeStamp(String createTimeStamp) {
        this.createTimeStamp = createTimeStamp;
    }

    public String getWaitingTimeStamp() {
        return waitingTimeStamp;
    }

    public void setWaitingTimeStamp(String waitingTimeStamp) {
        this.waitingTimeStamp = waitingTimeStamp;
    }

    public String getFinalTimeStamp() {
        return finalTimeStamp;
    }

    public void setFinalTimeStamp(String finalTimeStamp) {
        this.finalTimeStamp = finalTimeStamp;
    }
    //    public double getIntegration() {
//        return integration;
//    }
//
//    public void setIntegration(double integration) {
//        this.integration = integration;
//    }
//
//    public double getCoupon() {
//        return coupon;
//    }
//
//    public void setCoupon(double coupon) {
//        this.coupon = coupon;
//    }
//
//    public double getActualCost() {
//        return actualCost;
//    }
//
//    public void setActualCost(double actualCost) {
//        this.actualCost = actualCost;
//    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<OrderGrouponItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderGrouponItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
