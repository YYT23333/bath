package bath.data.admin;

import bath.data.dao.admin.AdminDao;
import bath.dataservice.admin.AdminDataService;
import bath.entity.admin.Admin;
import bath.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminDataServiceImpl implements AdminDataService {
	private final AdminDao adminDao;

	@Autowired
	public AdminDataServiceImpl(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public boolean isExistent(String username) {
		return !adminDao.findByUsername(username).isEmpty();
	}

	@Override
	public String add(Admin admin) {
		return adminDao.save(admin).getId();
	}

	@Override
	public Admin findById(String id) throws NotExistException {
		Optional<Admin> optionalAdmin = adminDao.findById(id);
		if(optionalAdmin.isPresent()) {
			return optionalAdmin.get();
		}else {
			throw new NotExistException("Admin ID", id);
		}
	}

	@Override
	public Admin findByUsername(String username) throws NotExistException {
		if (!adminDao.findByUsername(username).isEmpty()) {
			return adminDao.findByUsername(username).get(0);
		} else {
			throw new NotExistException("Admin username", username);
		}
	}

	@Override
	public List<Admin> getAll() {
		return adminDao.findAll();
	}

	@Override
	public void update(Admin admin) throws NotExistException {
		if(adminDao.findById(admin.getId()).isPresent()){
			adminDao.save(admin);
		}else{
			throw new NotExistException("Admin ID",admin.getId());
		}

	}


	@Override
	public void deleteById(String id) throws NotExistException {
		Optional<Admin> optionalAdmin = adminDao.findById(id);
		if (optionalAdmin.isPresent()) {
			Admin admin = optionalAdmin.get();
			adminDao.deleteById(id);
		} else {
			throw new NotExistException("Admin ID", id);
		}
	}
}
