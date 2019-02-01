package bath.bl.cart;

import bath.blservice.cart.CartBlService;
import bath.dataservice.cart.CartDataService;
import bath.dataservice.groupon.GrouponDataService;
import bath.dataservice.user.UserDataService;
import bath.entity.cart.Cart;
import bath.entity.user.User;
import bath.exception.NotExistException;
import bath.response.AddResponse;
import bath.response.InfoResponse;
import bath.response.cart.CartItemListResponse;
import bath.response.cart.CartItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
    public AddResponse addCartItem(String openid, String grouponId, int amount) throws NotExistException {
        Cart cart=new Cart();
        cart.setAmount(amount);
        cart.setGroupOn(grouponDataService.findById(grouponId));
        cart.setUser(userDataService.findByOpenid(openid));
        return new AddResponse(cartDataService.add(cart));
    }

    @Override
    @Transactional
    public InfoResponse deleteCartItem(int id) throws NotExistException {
        cartDataService.deleteById(id);
        return new InfoResponse();
    }

    @Override
    public InfoResponse updateCartItem(int id, int amount) throws NotExistException {
        Cart cart=cartDataService.findById(id);
        cart.setAmount(amount);
        cartDataService.update(cart);
        return new InfoResponse();
    }

    @Override
    public CartItemResponse findById(int id) throws NotExistException {
        return new CartItemResponse(cartDataService.findById(id));
    }

    @Override
    public CartItemListResponse findByUser(String openid) throws NotExistException {
        User user=userDataService.findByOpenid(openid);
        return new CartItemListResponse(cartDataService.findByUser(user));
    }
}
