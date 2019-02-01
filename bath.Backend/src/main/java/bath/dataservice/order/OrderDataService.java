package bath.dataservice.order;

import bath.entity.order.Order;
import bath.entity.user.User;
import bath.exception.NotExistException;

import java.util.List;

public interface OrderDataService {
    Order findById(String id) throws NotExistException;
    String add(Order order);
    void update(Order order) throws NotExistException;
    List<Order> findByUser(User user)throws  NotExistException;
    List<Order> findByUserAndState(User user,String state);
    List<Order> getAll();
}
