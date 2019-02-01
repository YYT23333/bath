package bath.dataservice.cart;

import bath.entity.cart.Cart;
import bath.entity.user.User;
import bath.exception.NotExistException;

import java.util.List;

public interface CartDataService {
    String add(Cart cartItem);
    void deleteById(int id) throws NotExistException;
    void update(Cart cartItem) throws NotExistException;
    Cart findById(int id) throws NotExistException;
    List<Cart> findByUser(User user);
}
