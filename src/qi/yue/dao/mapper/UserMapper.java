package qi.yue.dao.mapper;

import java.util.Map;

import qi.yue.entity.User;

public interface UserMapper {
	User findByUsernameAndPassword(Map<String, String> map);

	User findByUsername(String username);

	int insert(User user);

	int update(User user);
}