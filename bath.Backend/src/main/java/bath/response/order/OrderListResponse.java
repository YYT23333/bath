package bath.response.order;

import bath.entity.order.Order;
import bath.response.Response;

import java.util.ArrayList;
import java.util.List;

public class OrderListResponse extends Response {
    private List<OrderItem> orders;

    public OrderListResponse(List<Order> orders) {
        this.orders=new ArrayList<>();
        if(orders!=null && orders.size()>0){
            for(Order temp:orders){
                this.orders.add(new OrderItem(temp));
            }
        }
    }

    public OrderListResponse() {
    }

    public List<OrderItem> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderItem> orders) {
        this.orders = orders;
    }
}
