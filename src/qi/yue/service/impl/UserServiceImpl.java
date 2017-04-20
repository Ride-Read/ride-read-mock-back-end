package qi.yue.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qi.yue.common.MessageCommon;
import qi.yue.dao.mapper.UserMapper;
import qi.yue.dto.SimplifyUserDTO;
import qi.yue.dto.UserDTO;
import qi.yue.dto.assembler.UserDTOAssembler;
import qi.yue.entity.User;
import qi.yue.exception.BusinessException;
import qi.yue.exception.ParameterException;
import qi.yue.service.UserService;
import qi.yue.utils.CommonUtil;
import qi.yue.utils.DateUtil;
import qi.yue.utils.EncryptionUtil;

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

	/**
	 * 通过用户名查询用户信息
	 * 
	 * @param username
	 * @return UserDTO
	 * @throws BusinessException
	 */
	public UserDTO findByUsername(String username) throws BusinessException {
		try {
			return userMapper.findByUsername(username);
		} catch (Exception e) {
			throw new BusinessException(MessageCommon.STATUS_QUERY_FAIL, MessageCommon.FAIL_MESSAGE_QUERY_FAIL);
		}
	}

	/**
	 * 通过骑阅号查询用户信息
	 * 
	 * @param rideReadId
	 * @return UserDTO
	 * @throws BusinessException
	 */
	public UserDTO findRideReadId(String rideReadId) throws BusinessException {
		try {
			return userMapper.findByRideReadId(rideReadId);
		} catch (Exception e) {
			throw new BusinessException(MessageCommon.STATUS_QUERY_FAIL, MessageCommon.FAIL_MESSAGE_QUERY_FAIL);
		}
	}

	/**
	 * 通过电话号码查询用户信息
	 * 
	 * @param phonenumber
	 * @return UserDTO
	 * @throws BusinessException
	 */
	public UserDTO findByPhonenumber(String phonenumber) throws BusinessException {
		try {
			return userMapper.findByPhonenumber(phonenumber);
		} catch (Exception e) {
			throw new BusinessException(MessageCommon.STATUS_QUERY_FAIL, MessageCommon.FAIL_MESSAGE_QUERY_FAIL);
		}
	}

	@Override
	public UserDTO find(Integer id) {
		return userMapper.find(id);
	}

	@Transactional(rollbackFor = BusinessException.class)
	public int save(User user) throws BusinessException {
		try {
			return userMapper.insert(user);
		} catch (Exception e) {
			throw new BusinessException(MessageCommon.STATUS_SAVE_FAIL, MessageCommon.FAIL_MESSAGE_SAVE_FAIL);
		}

	}

	/**
	 * 更新用户表
	 * 
	 * @param user
	 * @return int
	 * @throws ParameterException
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = BusinessException.class)
	public int update(User user) throws ParameterException, BusinessException {
		if (CommonUtil.isNullOrEmpty(user)) {
			throw new ParameterException();
		}
		try {
			return userMapper.update(user);
		} catch (Exception e) {
			throw new BusinessException(MessageCommon.STATUS_UPDATE_FAIL, MessageCommon.FAIL_MESSAGE_UPDATE_FAIL);
		}
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

	/**
	 * 根据电话号码更新用户密码
	 * 
	 * @param password
	 * @param phonenumber
	 * @return int
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = BusinessException.class)
	public int updatePasswordByPhonenumber(String password, String phonenumber) throws BusinessException {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("password", password);
			map.put("phonenumber", phonenumber);
			return userMapper.updatePasswordByPhonenumber(map);
		} catch (Exception e) {
			throw new BusinessException(MessageCommon.STATUS_UPDATE_FAIL, MessageCommon.FAIL_MESSAGE_UPDATE_FAIL);
		}
	}

	// @Override
	// public UserDTO findThumbsUpUserByMid(Integer mid) {
	// return userMapper.findThumbsUpUserByMid(mid);
	// }

	@Override
	@Transactional(rollbackFor = Exception.class)
	public UserDTO login(String phonenumber, String password, BigDecimal latitude, BigDecimal longitude, Long timestamp)
			throws ParameterException, BusinessException {
		if (CommonUtil.isNullOrEmpty(phonenumber) || CommonUtil.isNullOrEmpty(password)
				|| CommonUtil.isNullOrEmpty(latitude) || CommonUtil.isNullOrEmpty(longitude)
				|| CommonUtil.isNullOrEmpty(timestamp)) {
			throw new ParameterException();
		}

		UserDTO userDTO = findByPhonenumber(phonenumber);
		if (CommonUtil.isNull(userDTO)) {
			throw new BusinessException(MessageCommon.STATUS_USER_NOT_EXIST, MessageCommon.FAIL_MESSAGE_USER_NOT_EXIST);
		}
		if (userDTO.getIs_login().equals(1)) {
			throw new BusinessException(MessageCommon.STATUS_USER_IS_LOGIN, MessageCommon.FAIL_MESSAGE_USER_IS_LOGIN);
		}

		password = EncryptionUtil.GetSHACode(password);
		if (!password.equals(userDTO.getPassword())) {
			throw new BusinessException(MessageCommon.STATUS_PASSWORD_WRONG, MessageCommon.FAIL_MESSAGE_PASSWORD_WRONG);
		}
		String token = EncryptionUtil.GetMD5Code(userDTO.getUid() + timestamp + MessageCommon.PUBLIC_KEY);
		userDTO.setToken(token);
		userDTO.setLatitude(latitude);
		userDTO.setLongitude(longitude);
		if (!CommonUtil.isNullOrEmpty(userDTO.getTagString())) {
			userDTO.setTags(userDTO.getTagString().split(","));
		}
		User user = new User();
		user.setId(userDTO.getUid());
		user.setToken(token);
		user.setIs_login(1);
		user.setLatitude(latitude);
		user.setLongitude(longitude);
		update(user);
		return userDTO;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void resetPassword(String phonenumber, String new_password, Long timestamp)
			throws ParameterException, BusinessException {
		if (CommonUtil.isNullOrEmpty(phonenumber) || CommonUtil.isNullOrEmpty(new_password)
				|| CommonUtil.isNullOrEmpty(timestamp)) {
			throw new ParameterException();
		}
		UserDTO userDTO = findByPhonenumber(phonenumber);
		if (CommonUtil.isNull(userDTO)) {
			throw new BusinessException(MessageCommon.STATUS_USER_NOT_EXIST, MessageCommon.FAIL_MESSAGE_USER_NOT_EXIST);
		}
		String passwordSHA = EncryptionUtil.GetSHACode(new_password);
		updatePasswordByPhonenumber(passwordSHA, phonenumber);
	}

	// throws ParameterException, BusinessException
	@Override
	@Transactional(rollbackFor = Exception.class)
	public UserDTO register(String phonenumber, String password, String face_url, String ride_read_id,
			String username) {
		if (CommonUtil.isNullOrEmpty(phonenumber) || CommonUtil.isNullOrEmpty(password)
				|| CommonUtil.isNullOrEmpty(face_url) || CommonUtil.isNullOrEmpty(ride_read_id)
				|| CommonUtil.isNullOrEmpty(username)) {
			throw new ParameterException();
		}

		UserDTO userByTele = findByPhonenumber(phonenumber);
		UserDTO userByRideReadId = findRideReadId(ride_read_id);
		if (!CommonUtil.isNullOrEmpty(userByTele) || !CommonUtil.isNullOrEmpty(userByRideReadId)) {
			if (!CommonUtil.isNullOrEmpty(userByTele)) {
				throw new BusinessException(MessageCommon.STATUS_PHONENUMBER_EXIST,
						MessageCommon.FAIL_MESSAGE_PHONENUMBER_EXIST);
			} else {
				throw new BusinessException(MessageCommon.STATUS_RIDE_READ_ID_EXIST,
						MessageCommon.FAIL_MESSAGE_RIDE_READ_ID_EXIST);
			}
		}

		String passwordSHA = EncryptionUtil.GetSHACode(password);
		User user = new User();
		user.setRideReadId(ride_read_id);
		user.setUsername(username);
		user.setPhonenumber(phonenumber);
		user.setPassword(passwordSHA);
		user.setFaceUrl(face_url);
		// 其实createdAt和updatedAt 可以直接写在SQL脚本里面，因为一般这个值都是固定为插入的日期，所以直接
		// 在脚本里面插入sys_date到这个字段就行
		Date date = new Date();
		user.setCreatedAt(date);
		user.setUpdatedAt(date);
		user.setSex(0);
		user.setFollower(0);
		user.setFollowing(0);
		user.setIs_login(0);
		userMapper.insert(user);
		return UserDTOAssembler.toDto(user);
	}

	@Override
	public boolean verify(String ride_read_id, Long timestamp) throws ParameterException, BusinessException {
		if (CommonUtil.isNullOrEmpty(ride_read_id) || CommonUtil.isNullOrEmpty(timestamp)) {
			throw new ParameterException();
		}
		UserDTO userDTO = findRideReadId(ride_read_id);
		return !CommonUtil.isNull(userDTO);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public UserDTO updateUserInfo(String career, String phonenumber, String location, String birthday, String username,
			String face_url, Integer uid, String token, String signature, String school, Integer sex,
			BigDecimal latitude, BigDecimal longitude, String hometown, String[] tags, Long timestamp)
			throws ParameterException, BusinessException {

		if (CommonUtil.isNullOrEmpty(career) || CommonUtil.isNullOrEmpty(phonenumber)
				|| CommonUtil.isNullOrEmpty(location) || CommonUtil.isNullOrEmpty(birthday)
				|| CommonUtil.isNullOrEmpty(username) || CommonUtil.isNullOrEmpty(face_url) || CommonUtil.isNull(uid)
				|| CommonUtil.isNullOrEmpty(token) || CommonUtil.isNullOrEmpty(signature)
				|| CommonUtil.isNullOrEmpty(school) || CommonUtil.isNull(sex) || CommonUtil.isNull(latitude)
				|| CommonUtil.isNull(longitude) || CommonUtil.isNullOrEmpty(hometown) || CommonUtil.isNullOrEmpty(tags)
				|| CommonUtil.isNull(timestamp)) {
			throw new ParameterException();
		}
		UserDTO userDTO = find(uid);
		if (CommonUtil.isNull(userDTO)) {
			throw new BusinessException(MessageCommon.STATUS_USER_NOT_EXIST, MessageCommon.FAIL_MESSAGE_USER_NOT_EXIST);
		}
		// String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp +
		// MessageCommon.PUBLIC_KEY);
		User user = new User();
		user.setId(uid);
		user.setCareer(career);
		user.setPhonenumber(phonenumber);
		user.setLocation(location);
		user.setFaceUrl(face_url);
		user.setId(uid);
		user.setToken(token);
		user.setSignature(signature);
		user.setUsername(username);
		user.setSchool(school);
		user.setLatitude(latitude);
		user.setLongitude(longitude);
		user.setSex(sex);
		user.setHometown(hometown);
		if (!CommonUtil.isNullOrEmpty(tags)) {
			StringBuffer buffer = new StringBuffer();
			for (String str : tags) {
				buffer.append(str);
			}
			user.setTags(buffer.toString());
		}

		user.setUpdatedAt(new Date());
		try {
			user.setBirthday(DateUtil.strToDate(birthday, MessageCommon.DATE_TIME_FORMAT));
		} catch (ParseException e) {
			throw new BusinessException(MessageCommon.STATUS_DATE_FORMAT_ERROR,
					MessageCommon.FAIL_MESSAGE_DATE_FORMAT_ERROR);
		}
		update(user);
		UserDTO dto = findByPhonenumber(phonenumber);
		if (!CommonUtil.isNullOrEmpty(dto.getTagString())) {
			dto.setTags(dto.getTagString().split(","));
		}
		return dto;
	}

	@Override
	public List<SimplifyUserDTO> findMoreUser(Integer uid, String token, Long timestamp, String userIds)
			throws ParameterException, BusinessException {
		if (CommonUtil.isNullOrEmpty(uid) || CommonUtil.isNullOrEmpty(token) || CommonUtil.isNullOrEmpty(timestamp)
				|| CommonUtil.isNullOrEmpty(userIds)) {
			throw new ParameterException();
		}
		String[] userIdList = userIds.split(",");
		List<String> list = Arrays.asList(userIdList);
		List<SimplifyUserDTO> data = userMapper.findByIds(list);
		return data;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void loginOut(Integer uid, String token, Long timestamp) {
		if (CommonUtil.isNullOrEmpty(uid) || CommonUtil.isNullOrEmpty(token) || CommonUtil.isNullOrEmpty(timestamp)) {
			throw new ParameterException();
		}
		User user = new User();
		user.setId(uid);
		user.setIs_login(0);
		user.setToken(null);
		update(user);
	}
}
