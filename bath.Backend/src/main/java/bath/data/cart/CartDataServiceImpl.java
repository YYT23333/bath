package bath.data.cart;

import bath.data.dao.cart.CartDao;
import bath.dataservice.cart.CartDataService;
import bath.entity.cart.Cart;
import bath.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartDataServiceImpl implements CartDataService {
    private final CartDao cartDao;
    @Autowired
    public CartDataServiceImpl(CartDao cartDao){
        this.cartDao=cartDao;
    }

    @Override
    public void add(Cart cartItem) {
        cartDao.save(cartItem);
    }

    @Override
    public void deleteById(String id) throws NotExistException {
        if(cartDao.findById(id).isPresent()){
            cartDao.deleteById(id);
        }else{
            throw new NotExistException("Cart ID",id);
        }
    }

    @Override
    public void update(Cart cartItem) throws NotExistException {
        if(cartDao.findById(cartItem.getId()).isPresent()){
            cartDao.save(cartItem);
        }else{
            throw new NotExistException("Cart ID",cartItem.getId());
        }
    }

    @Override
    public Cart findById(String id) throws NotExistException {
        Optional<Cart> cartItem=cartDao.findById(id);
        if(cartItem.isPresent()){
            return cartItem.get();
        }else{
            throw new NotExistException("Cart ID",id);
        }
    }
}
