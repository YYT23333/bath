package bath.response.order;

import bath.entity.order.Order;
import bath.entity.order.OrderGrouponItem;
import bath.response.Response;

public class OrderResponse extends Response {

    private OrderItem order;
    public OrderResponse() {
    }

    public OrderResponse(Order order) {
        this.order = new OrderItem(order);
    }

    public OrderItem getOrder() {
        return order;
    }

    public void setOrder(OrderItem order) {
        this.order = order;
    }
}