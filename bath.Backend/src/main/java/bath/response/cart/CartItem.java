package bath.response.cart;

import bath.entity.cart.Cart;
import bath.entity.groupon.Groupon;
import bath.response.groupon.GrouponItem;

import javax.persistence.*;

public class CartItem {
    private int id;
    private int amount;
    private String openid;
    private GrouponItem groupOn;

    public CartItem() {
    }

    public CartItem(Cart cart) {
        this.id=cart.getId();
        this.openid=cart.getUser().getOpenid();
        this.amount = cart.getAmount();
        this.groupOn=new GrouponItem(cart.getGroupOn());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public GrouponItem getGroupOn() {
        return groupOn;
    }

    public void setGroupOn(GrouponItem groupOn) {
        this.groupOn = groupOn;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
