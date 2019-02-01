package bath.bl.admin;


import bath.blservice.admin.AdminBlService;
import bath.dataservice.admin.AdminDataService;
import bath.entity.admin.Admin;
import bath.exception.DuplicateUsernameException;
import bath.exception.NotExistException;
import bath.response.AddResponse;
import bath.response.BoolResponse;
import bath.response.InfoResponse;
import bath.response.admin.AdminItem;
import bath.response.admin.AdminListResponse;
import bath.response.admin.AdminResponse;
import bath.security.jwt.JwtService;
import bath.security.jwt.JwtUser;
import bath.security.jwt.JwtUserDetailsService;
import bath.util.FormatDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AdminBlServiceImpl implements AdminBlService {
	private final AdminDataService adminDataService;
	private final JwtUserDetailsService jwtUserDetailsService;
	private final JwtService jwtService;
	private final static long EXPIRATION = 604800;
	@Autowired
	public AdminBlServiceImpl(AdminDataService adminDataService, JwtUserDetailsService jwtUserDetailsService,JwtService jwtService) {
		this.adminDataService = adminDataService;
		this.jwtUserDetailsService = jwtUserDetailsService;
		this.jwtService = jwtService;
	}

	@Override
	public BoolResponse isAdminUsernameExistent(String username) {
		return new BoolResponse(adminDataService.isExistent(username), "ok");
	}

	@Override
	public String loginAdmin(String username, String password) {
		try {
			JwtUser jwtUser = (JwtUser) jwtUserDetailsService.loadUserByUsername(username);
			String token="";
			token = jwtService.generateToken(jwtUser, EXPIRATION);
			if(adminDataService.isExistent(username)
					&& adminDataService.findByUsername(username).getPassword().equals(password)){
				return token;
			}

			return null;
		} catch (NotExistException exception) {
			return null;
		}
	}

	@Override
	public AddResponse addAdmin(String username, String password, String limits, String date, String face) throws DuplicateUsernameException {
		if (!adminDataService.isExistent(username)) {
			return new AddResponse(adminDataService.add(new Admin(username, password, limits, date, face)));
		} else {
			throw new DuplicateUsernameException(username);
		}
	}

	@Override
	public AdminResponse getAdmin(String id) throws NotExistException {
		return new AdminResponse(new AdminItem(adminDataService.findById(id)));
	}

	@Override
	public AdminResponse getAdminByUsername(String username) throws NotExistException {
		return new AdminResponse(new AdminItem(adminDataService.findByUsername(username)));
	}

	@Override
	public AdminListResponse getAdminList() {
		List<Admin> adminList = adminDataService.getAll();
		List<AdminItem> adminItemList = new ArrayList<>();
		for(Admin admin:adminList) {
			adminItemList.add(new AdminItem(admin));
		}
		return new AdminListResponse(adminItemList);
	}

	@Override
	public InfoResponse updateAdmin(String id, String username, String password, String limits, String date, String face) throws NotExistException {
		Admin admin=new Admin(username,password,limits,date,face);
		admin.setId(id);
		adminDataService.update(admin);
		return new InfoResponse();
	}

	@Override
	public BoolResponse deleteAdmin(String id) throws NotExistException {
		adminDataService.deleteById(id);
		return new BoolResponse(true, "");
	}
}
