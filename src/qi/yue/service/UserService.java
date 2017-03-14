package qi.yue.service;

import qi.yue.dto.UserDto;
import qi.yue.entity.User;

public interface UserService {
	public UserDto findByUsernameAndPassword(String username, String password);

	public UserDto findByUidAndToken(int uid, String token);

	public UserDto findByUsername(String username);

	public UserDto find(int id);

	public int save(User user);

	public int update(User user);

	public int updateTokenById(String token, int uid);

	public int updatePasswordByUsernamae(String password, String username);
}
