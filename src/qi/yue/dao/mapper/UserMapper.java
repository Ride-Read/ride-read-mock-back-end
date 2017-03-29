package qi.yue.dao.mapper;

import java.util.Map;

import qi.yue.dto.UserDTO;
import qi.yue.entity.User;

public interface UserMapper {
	UserDTO findByUsernameAndPassword(Map<String, String> map);

	UserDTO findByUidAndToken(Map<String, Object> map);

	UserDTO findByUsername(String username);

	UserDTO findByPhonenumber(String phonenumber);

	UserDTO findThumbsUpUserByMid(Integer mid);

	UserDTO find(Integer id);

	int insert(User user);

	int update(User user);

	int updateTokenById(Map<String, Object> map);

	int updatePasswordByUsernamae(Map<String, Object> map);

	int updatePasswordByPhonenumber(Map<String, Object> map);
}