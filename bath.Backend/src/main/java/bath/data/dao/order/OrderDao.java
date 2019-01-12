package bath.data.dao.order;

import bath.entity.order.Order;
import bath.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDao extends JpaRepository<Order, String> {
    List<Order> findByUser(User user);
    List<Order> findByUserAndAndOrderState(User user,String orderState);
}
