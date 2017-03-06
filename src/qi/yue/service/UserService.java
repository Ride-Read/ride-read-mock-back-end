package qi.yue.service;

import qi.yue.entity.User;

public interface UserService {
	public User findByUsernameAndPassword(String username, String password);

	public User findByUsername(String username);

	public int save(User user);

	public int update(User user);
}
