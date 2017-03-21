package qi.yue.service;

import qi.yue.dto.UserDTO;
import qi.yue.entity.User;

public interface UserService {
	public UserDTO findByUsernameAndPassword(String username, String password);

	public UserDTO findByUidAndToken(int uid, String token);

	public UserDTO findByUsername(String username);
	
	public UserDTO findByPhonenumber(String phonenumber);

	public UserDTO find(int id);

	public int save(User user);

	public int update(User user);

	public int updateTokenById(String token, int uid);

	public int updatePasswordByUsernamae(String password, String username);
}
