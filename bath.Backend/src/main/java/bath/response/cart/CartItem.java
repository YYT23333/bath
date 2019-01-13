package bath.response.cart;

import bath.entity.cart.Cart;
import bath.entity.groupon.Groupon;

import javax.persistence.*;

public class CartItem { @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private int amount;
    private Groupon groupOn;

    public CartItem() {
    }

    public CartItem(Cart cart) {
        this.id=cart.getId();
        this.amount = cart.getAmount();
        this.groupOn=cart.getGroupOn();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Groupon getGroupOn() {
        return groupOn;
    }

    public void setGroupOn(Groupon groupOn) {
        this.groupOn = groupOn;
    }
}
