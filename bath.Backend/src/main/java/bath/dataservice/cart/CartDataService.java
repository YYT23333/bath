package bath.dataservice.cart;

import bath.entity.cart.Cart;
import bath.exception.NotExistException;

public interface CartDataService {
    void add(Cart cartItem);
    void deleteById(String id) throws NotExistException;
    void update(Cart cartItem) throws NotExistException;
    Cart findById(String id) throws NotExistException;
}
