package bath.bl.cart;

import bath.blservice.cart.CartBlService;
import bath.dataservice.cart.CartDataService;
import bath.dataservice.groupon.GrouponDataService;
import bath.dataservice.user.UserDataService;
import bath.entity.cart.Cart;
import bath.exception.NotExistException;
import bath.response.InfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartBlServiceImpl implements CartBlService {
    private final CartDataService cartDataService;
    private final GrouponDataService grouponDataService;
    private final UserDataService userDataService;
    @Autowired
    public CartBlServiceImpl(CartDataService cartDataService,GrouponDataService grouponDataService,UserDataService userDataService){
        this.cartDataService=cartDataService;
        this.grouponDataService=grouponDataService;
        this.userDataService=userDataService;
    }

    @Override
    public InfoResponse addCartItem(String openid, String grouponId, int amount) throws NotExistException {
        Cart cart=new Cart();
        cart.setAmount(amount);
        cart.setGroupOn(grouponDataService.findById(grouponId));
        cart.setUser(userDataService.findByOpenid(openid));
        cartDataService.add(cart);
        return new InfoResponse();
    }

    @Override
    public InfoResponse deleteCartItem(String id) throws NotExistException {
        cartDataService.deleteById(id);
        return new InfoResponse();
    }

    @Override
    public InfoResponse updateCartItem(String id, int amount) throws NotExistException {
        Cart cart=cartDataService.findById(id);
        cart.setAmount(amount);
        cartDataService.update(cart);
        return new InfoResponse();
    }
}
