package bath.data.user;

import bath.data.dao.user.UserDao;
import bath.dataservice.user.UserDataService;
import bath.entity.address.Address;
import bath.entity.coupon.Coupon;
import bath.entity.groupon.Groupon;
import bath.entity.order.Order;
import bath.entity.user.Level;
import bath.entity.user.User;
import bath.exception.NotExistException;
import bath.publicdatas.account.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDataServiceImpl implements UserDataService {
	private final UserDao userDao;

	@Autowired
	public UserDataServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}



	@Override
	public void add(User user) {
		userDao.save(user);
	}

	@Override
	public User findByOpenid(String openid) throws NotExistException {
		Optional<User> optionalUser = userDao.findById(openid);
		if(optionalUser.isPresent()) {
			return optionalUser.get();
		} else {
			throw new NotExistException("User Openid", openid);
		}
	}

	@Override
	public List<User> getAll() {
		return userDao.findAll();
	}

	@Override
	public void update(User user) throws NotExistException {
		if(userDao.findByOpenid(user.getOpenid()).isPresent()){
			userDao.save(user);
		}else{
			throw new NotExistException("User Openid",user.getOpenid());
		}
	}

	@Override
	public void deleteUByOpenid(String openid) throws NotExistException {
		Optional<User> optionalUser = userDao.findById(openid);
		if (optionalUser.isPresent()) {
			userDao.deleteById(openid);
		} else {
			throw new NotExistException("User openid", openid);
		}
	}


}
