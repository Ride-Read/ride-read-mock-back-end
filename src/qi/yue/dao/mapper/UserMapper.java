package qi.yue.dao.mapper;

import java.util.Map;

import qi.yue.dto.UserDto;
import qi.yue.entity.User;

public interface UserMapper {
	UserDto findByUsernameAndPassword(Map<String, String> map);

	UserDto findByUidAndToken(Map<String, Object> map);

	UserDto findByUsername(String username);

	UserDto find(int id);

	int insert(User user);

	int update(User user);

	int updateTokenById(Map<String, Object> map);

	int updatePasswordByUsernamae(Map<String, Object> map);
}