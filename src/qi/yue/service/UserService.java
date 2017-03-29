package qi.yue.service;

import qi.yue.dto.UserDTO;
import qi.yue.entity.User;

public interface UserService {
	public UserDTO findByUsernameAndPassword(String username, String password);

	public UserDTO findByUidAndToken(Integer uid, String token);

	public UserDTO findByUsername(String username);

	public UserDTO findByPhonenumber(String phonenumber);

	public UserDTO find(Integer id);

	UserDTO findThumbsUpUserByMid(Integer mid);

	public int save(User user);

	public int update(User user);

	public int updateTokenById(String token, Integer uid);

	public int updatePasswordByUsernamae(String password, String username);

	public int updatePasswordByPhonenumber(String password, String phonenumber);
}
