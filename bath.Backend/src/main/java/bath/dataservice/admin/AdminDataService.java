package bath.dataservice.admin;
import bath.entity.admin.Admin;
import bath.exception.NotExistException;

import java.util.List;
import java.util.Optional;

public interface AdminDataService {

	boolean isExistent(String username);

	String add(Admin admin);

	Admin findById(String id) throws NotExistException;

	Admin findByUsername(String username) throws NotExistException;

	List<Admin> getAll();

	void update(Admin admin)throws NotExistException;

	//注意：删除Admin时统一使用deleteAdminById（封装了相关数据连锁删除）
	void deleteById(String id) throws NotExistException;
}
