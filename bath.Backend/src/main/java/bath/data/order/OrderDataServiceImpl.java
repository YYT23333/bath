package bath.data.order;

import bath.data.dao.order.OrderDao;
import bath.dataservice.order.OrderDataService;
import bath.entity.order.Order;
import bath.entity.user.User;
import bath.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDataServiceImpl implements OrderDataService {
    private final OrderDao orderDao;
    @Autowired
    public OrderDataServiceImpl(OrderDao orderDao){
        this.orderDao=orderDao;
    }

    @Override
    public Order findById(String id) throws NotExistException {
        Optional<Order> optionalOrder = orderDao.findById(id);
        if(optionalOrder.isPresent()) {
            return optionalOrder.get();
        } else {
            throw new NotExistException("Order ID", id);
        }
    }

    @Override
    public void add(Order order) {
        orderDao.save(order);
    }

    @Override
    public void update(Order order) throws NotExistException {
        if(orderDao.findById(order.getId()).isPresent()){
        orderDao.save(order);}else{
            throw new NotExistException("Order ID",order.getId());
        }
    }


    @Override
    public List<Order> findByUser(User user) {
        return orderDao.findByUser(user);
    }

    @Override
    public List<Order> findByUserAndState(User user, String state) {
        return orderDao.findByUserAndAndOrderState(user,state);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.findAll();
    }
}
