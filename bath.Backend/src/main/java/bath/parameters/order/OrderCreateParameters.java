package bath.parameters.order;

import bath.entity.order.OrderGrouponItem;

import java.util.List;

public class OrderCreateParameters {
    private String openid;
    private List<OrderGrouponItem> orderItems;
    private double total;


    public OrderCreateParameters(String openid, List<OrderGrouponItem> orderItems, double total) {
        this.openid = openid;
        this.orderItems = orderItems;
        this.total=total;
    }

    public OrderCreateParameters() {
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public List<OrderGrouponItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderGrouponItem> orderItems) {
        this.orderItems = orderItems;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
