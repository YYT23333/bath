package bath.response.cart;

import bath.entity.cart.Cart;
import bath.response.Response;

import java.util.ArrayList;
import java.util.List;

public class CartItemListResponse extends Response {
    private List<CartItem> cartItems;

    public CartItemListResponse() {
    }

    public CartItemListResponse(List<Cart> cartItems) {
        this.cartItems=new ArrayList<>();
        if(cartItems!=null && cartItems.size()>0){
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
