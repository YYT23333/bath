package bath.response.order;

import bath.entity.order.Order;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderItem {
    private String id;
    private String openid;
    private double total;
    private Date date;
    private List<OrderGrouponItem> orderItems;
    private String orderState;
    private String createTimeStamp;
    private String finalTimeStamp;
    private String waitingTimeStamp;

    public OrderItem(Order order) {
        this.id = order.getId();
        this.openid=order.getUser().getOpenid();
        this.total = order.getTotal();
        this.date = order.getDate();
        this.orderItems = new ArrayList<>();
        if (order.getOrderItems() != null && order.getOrderItems().size() > 0) {
            for (bath.entity.order.OrderGrouponItem temp : order.getOrderItems()) {
                orderItems.add(new OrderGrouponItem(temp));
            }
        }
        this.orderState = order.getOrderState();
        this.createTimeStamp = order.getCreateTimeStamp();
        this.finalTimeStamp = order.getFinalTimeStamp();
        this.waitingTimeStamp = order.getWaitingTimeStamp();
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

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

    public String getCreateTimeStamp() {
        return createTimeStamp;
    }

    public void setCreateTimeStamp(String createTimeStamp) {
        this.createTimeStamp = createTimeStamp;
    }

    public String getFinalTimeStamp() {
        return finalTimeStamp;
    }

    public void setFinalTimeStamp(String finalTimeStamp) {
        this.finalTimeStamp = finalTimeStamp;
    }

    public String getWaitingTimeStamp() {
        return waitingTimeStamp;
    }

    public void setWaitingTimeStamp(String waitingTimeStamp) {
        this.waitingTimeStamp = waitingTimeStamp;
    }
}
