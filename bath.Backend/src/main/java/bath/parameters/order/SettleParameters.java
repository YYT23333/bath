package bath.parameters.order;

import bath.entity.order.OrderGrouponItem;

import java.util.List;

public class SettleParameters {
    private List<OrderGrouponItem> orderItems;

    public SettleParameters() {
    }

    public SettleParameters(List<OrderGrouponItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<OrderGrouponItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderGrouponItem> orderItems) {
        this.orderItems = orderItems;
    }
}
