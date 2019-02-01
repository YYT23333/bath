package bath.response.user;

import bath.entity.address.Address;
import bath.entity.cart.Cart;
import bath.entity.coupon.Coupon;
import bath.entity.groupon.Groupon;
import bath.entity.order.Order;
import bath.entity.user.User;
import bath.publicdatas.account.Role;
import bath.response.address.AddressItem;
import bath.response.cart.CartItem;
import bath.response.coupon.CouponItem;
import bath.response.order.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class UserItem {
	private String openid;//用户微信编号
	private String username; //用户名
	private String avatarUrl;//头像
	private Role role;
	private String phone;
	private String levelName;
	private int integral;

	public UserItem(User user) {
		this.openid = user.getOpenid();
		this.username = user.getUsername();
		this.avatarUrl = user.getAvatarUrl();
		this.role = user.getRole();
		this.phone = user.getPhone();
		this.levelName = user.getLevel();
		this.integral=user.getintegral();
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

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public void setintegral(int integral) {
		this.integral = integral;
	}

	public int getintegral() {
		return integral;
	}
}