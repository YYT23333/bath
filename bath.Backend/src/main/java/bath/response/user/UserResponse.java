package bath.response.user;

import bath.entity.user.User;
import bath.response.Response;

public class UserResponse extends Response {
	private UserItem user;

	public UserResponse() {
	}

	public UserResponse(User user) {
		this.user = new UserItem(user);
	}

	public UserItem getUser() {
		return user;
	}

	public void setUser(UserItem user) {
		this.user = user;
	}
}
