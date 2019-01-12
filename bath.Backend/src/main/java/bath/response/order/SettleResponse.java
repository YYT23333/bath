package bath.response.order;

import bath.entity.order.OrderGrouponItem;
import bath.response.Response;

import java.util.List;

public class SettleResponse extends Response {
    private double total;
    private List<OrderGrouponItem> orderItems;

    public SettleResponse() {
    }

    public SettleResponse(double total, List<OrderGrouponItem> orderItems) {
        this.total = total;
        this.orderItems = orderItems;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<OrderGrouponItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderGrouponItem> orderItems) {
        this.orderItems = orderItems;
    }
}
