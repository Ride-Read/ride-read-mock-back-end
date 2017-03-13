package qi.yue.service;

import qi.yue.entity.User;

public interface UserService {
	public User findByUsernameAndPassword(String username, String password);

	public User findByUidAndToken(int uid, String token);

	public User findByUsername(String username);

	public User find(int id);

	public int save(User user);

	public int update(User user);

	public int updateTokenById(String token, int uid);

	public int updatePasswordByUsernamae(String password, String username);
}
