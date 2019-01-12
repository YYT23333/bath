package bath.response.order;

import bath.entity.order.Order;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderItem {
    private String id;
    private double total;
    private Date date;
    private List<OrderGrouponItem> orderItems;
    private String orderState;
    private String createTimeStamp;
    private String finalTimeStamp;
    private String waitingTimeStamp;

    public OrderItem(Order order) {
        this.id = order.getId();
        this.total = order.getTotal();
        this.date = order.getDate();
        if (order.getOrderItems() != null && orderItems.size() > 0) {
            this.orderItems = new ArrayList<>();
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
