package qi.yue.dao.mapper;

import java.util.Map;

import qi.yue.entity.User;

public interface UserMapper {
	User findByUsernameAndPassword(Map<String, String> map);

	User findByUidAndToken(Map<String, Object> map);

	User findByUsername(String username);

	User find(int id);

	int insert(User user);

	int update(User user);

	int updateTokenById(Map<String, Object> map);

	int updatePasswordByUsernamae(Map<String, Object> map);
}