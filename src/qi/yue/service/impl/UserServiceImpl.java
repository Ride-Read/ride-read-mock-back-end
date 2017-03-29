package qi.yue.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qi.yue.dao.mapper.UserMapper;
import qi.yue.dto.UserDTO;
import qi.yue.entity.User;
import qi.yue.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserMapper userMapper;

	@Override
	public UserDTO findByUsernameAndPassword(String username, String password) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", username);
		map.put("password", password);
		return userMapper.findByUsernameAndPassword(map);
	}

	@Override
	public UserDTO findByUidAndToken(Integer uid, String token) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", uid);
		map.put("token", token);
		return userMapper.findByUidAndToken(map);
	}

	@Override
	public UserDTO findByUsername(String username) {
		return userMapper.findByUsername(username);
	}

	@Override
	public UserDTO findByPhonenumber(String phonenumber) {
		// TODO Auto-generated method stub
		return userMapper.findByPhonenumber(phonenumber);
	}

	@Override
	public UserDTO find(Integer id) {
		return userMapper.find(id);
	}

	@Override
	@Transactional
	public int save(User user) {
		return userMapper.insert(user);

	}

	@Override
	public int update(User user) {
		return userMapper.update(user);
	}

	@Override
	@Transactional
	public int updateTokenById(String token, Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("token", token);
		map.put("id", id);
		return userMapper.updateTokenById(map);
	}

	@Override
	@Transactional
	public int updatePasswordByUsernamae(String password, String username) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("password", password);
		map.put("username", username);
		return userMapper.updatePasswordByUsernamae(map);
	}

	@Override
	@Transactional
	public int updatePasswordByPhonenumber(String password, String phonenumber) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("password", password);
		map.put("phonenumber", phonenumber);
		return userMapper.updatePasswordByPhonenumber(map);
	}

	@Override
	public UserDTO findThumbsUpUserByMid(Integer mid) {
		return userMapper.findThumbsUpUserByMid(mid);
	}

}
