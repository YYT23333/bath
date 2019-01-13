package bath.response.cart;

import bath.entity.cart.Cart;
import bath.response.Response;

import java.util.ArrayList;
import java.util.List;

public class CartResponse extends Response {
    private List<CartItem> cartItems;

    public CartResponse() {
    }

    public CartResponse(List<Cart> cartItems) {
        if(cartItems!=null && cartItems.size()>0){
            this.cartItems=new ArrayList<>();
            for(Cart temp:cartItems){
                this.cartItems.add(new CartItem(temp));
            }
        }
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
