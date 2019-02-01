package bath.entity.user;

import bath.entity.address.Address;
import bath.entity.cart.Cart;
import bath.entity.coupon.Coupon;
import bath.entity.integral.ExchangeRecord;
import bath.entity.order.Order;
import bath.publicdatas.account.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class User {
    @Id
    @Column
    private String openid;//用户微信编号
    @Column(name="username")
    private String username; //用户名
    @Column(name = "role")
    private Role role;//角色
    @Column(name="avatarUrl")
    private String avatarUrl; //用户头像
    @Column(name="phone")
    private String phone; //电话号码
    @Column(name="level")
    private String level; //用户所属会员等级
    @Column(name="integral")
    private int integral;//积分
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "user")
    private List<Order> orders;//订单
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "user")
    private List<Cart> cart;//购物车
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "user")
    private List<Address> addresses;//地址
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "user")
    private List<Coupon> coupons;//优惠券
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "user")
    private List<ExchangeRecord> exchangeRecords;

    public User() {
    }

    public User(String openid, String username, String avatarUrl) {
        this.openid = openid;
        this.username = username;
        this.role = new Role("ROLE_USER");
        this.avatarUrl = avatarUrl;
        this.phone = "";
        this.level = "common";
        this.integral = 0;
        //this.balance = 0;
        this.orders = new ArrayList<>();
        this.cart = new ArrayList<>();
        this.addresses = new ArrayList<>();
        this.coupons = new ArrayList<>();
        this.exchangeRecords=new ArrayList<>();

    }

    public User(String openid, String username, String avatarUrl, String phone) {
        this.openid = openid;
        this.username = username;
        this.role = new Role("ROLE_USER");
        this.avatarUrl = avatarUrl;
        this.phone = phone;
        this.level = "common";
        //this.exchange = 0;
        //this.balance = 0;
        this.orders = new ArrayList<>();
        this.cart = new ArrayList<>();
        this.addresses = new ArrayList<>();
        this.coupons = new ArrayList<>();
        this.exchangeRecords=new ArrayList<>();

    }

    public User(String openid, String username, Role role, String avatarUrl, String phone, String level, int integral,List<Order> orders, List<Cart> cart, List<Address> addresses,List<Coupon> coupons,List<ExchangeRecord> exchangeRecords) {
        this.openid = openid;
        this.username = username;
        this.role = role;
        this.avatarUrl = avatarUrl;
        this.phone = phone;
        this.level = level;
        this.integral=integral;
        this.orders = orders;
        this.cart = cart;
        this.addresses = addresses;
        this.coupons=coupons;
        this.exchangeRecords=exchangeRecords;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLevel() { return level; }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getintegral() {
        return integral;
    }

    public void setintegral(int integral) {
        this.integral = integral;}

//    public double getBalance() {
//        return balance;
//    }
//
//    public void setBalance(double balance) {
//        this.balance = balance;
//    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Cart> getCart() {
        return cart;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<ExchangeRecord> getExchangeRecords() {
        return exchangeRecords;
    }

    public void setExchangeRecords(List<ExchangeRecord> exchangeRecords) {
        this.exchangeRecords = exchangeRecords;
    }
}
