package bath.dataservice.user;

import bath.entity.address.Address;
import bath.entity.coupon.Coupon;
import bath.entity.groupon.Groupon;
import bath.entity.order.Order;
import bath.entity.user.User;
import bath.exception.NotExistException;
import bath.publicdatas.account.Role;
import java.util.List;

public interface UserDataService {

	String add(User user);

	User findByOpenid(String openid) throws NotExistException;

	List<User> getAll();

	void update(User user) throws NotExistException;

	void deleteUByOpenid(String openid) throws NotExistException;

}
