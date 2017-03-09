package qi.yue.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import qi.yue.dao.mapper.UserMapper;
import qi.yue.entity.User;
import qi.yue.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserMapper userMapper;

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", username);
		map.put("password", password);
		return userMapper.findByUsernameAndPassword(map);
	}

	@Override
	public User findByUidAndToken(int uid, String token) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("token", token);
		return userMapper.findByUidAndToken(map);
	}

	@Override
	public User findByUsername(String username) {
		return userMapper.findByUsername(username);
	}

	@Override
	public User findByUid(int uid) {
		return userMapper.findByUid(uid);
	}

	@Override
	public int save(User user) {
		return userMapper.insert(user);
	}

	@Override
	public int update(User user) {
		return userMapper.update(user);
	}

	@Override
	public int updateTokenByUid(String token, int uid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("token", token);
		map.put("uid", uid);
		return userMapper.updateTokenByUid(map);
	}
}
