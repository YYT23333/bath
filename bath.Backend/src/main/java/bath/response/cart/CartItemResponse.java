package bath.response.cart;

import bath.entity.cart.Cart;
import bath.response.Response;
public class CartItemResponse extends Response {
    private CartItem cartItem;
    public CartItemResponse(){}
    public CartItemResponse(Cart cart){
        this.cartItem=new CartItem(cart);
    }

    public CartItem getCartItem() {
        return cartItem;
    }

    public void setCartItem(CartItem cartItem) {
        this.cartItem = cartItem;
    }
}