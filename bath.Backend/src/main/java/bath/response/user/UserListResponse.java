package bath.response.user;

import bath.entity.user.User;
import bath.response.Response;

import java.util.ArrayList;
import java.util.List;

public class UserListResponse extends Response {
	private List<UserItem> users;

	public UserListResponse() {
	}

	public UserListResponse(List<User> users) {
		this.users=new ArrayList<>();
		if(users!=null && users.size()>0){
			for(User temp:users){
				this.users.add(new UserItem(temp));
			}
		}
	}

	public List<UserItem> getUsers() {
		return users;
	}

	public void setUsers(List<UserItem> users) {
		this.users = users;
	}
}
