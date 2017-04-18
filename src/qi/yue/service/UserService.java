package qi.yue.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import qi.yue.dto.ResponseDTO;
import qi.yue.dto.SimplifyUserDTO;
import qi.yue.dto.UserDTO;
import qi.yue.entity.User;
import qi.yue.exception.BusinessException;
import qi.yue.exception.ParameterException;

public interface UserService {
	public UserDTO findByUsernameAndPassword(String username, String password);

	public UserDTO findByUidAndToken(Integer uid, String token);

	// public UserDTO findByUsername(String username);

	// public UserDTO findByPhonenumber(String phonenumber) throws
	// BusinessException;

	/**
	 * 通过id查询用户信息
	 * 
	 * @param id
	 * @return
	 */
	public UserDTO find(Integer id);

	// UserDTO findThumbsUpUserByMid(Integer mid);

	// public int save(User user);

	// public int update(User user) throws ParameterException,BusinessException;

	public int updateTokenById(String token, Integer uid);

	public int updatePasswordByUsernamae(String password, String username);

	// public int updatePasswordByPhonenumber(String password, String
	// phonenumber) throws ParameterException, BusinessException;

	public UserDTO login(String phonenumber, String password, BigDecimal latitude, BigDecimal longitude, Long timestamp)
			throws ParameterException, BusinessException;

	/**
	 * 重置密码
	 * 
	 * @param phonenumber
	 * @param new_password
	 * @param timestamp
	 * @throws ParameterException
	 * @throws BusinessException
	 */
	public void resetPassword(String phonenumber, String new_password, Long timestamp)
			throws ParameterException, BusinessException;

	/**
	 * 用户注册
	 * 
	 * @param phonenumber
	 * @param password
	 * @param face_url
	 * @param nickname
	 * @param username
	 * @return User
	 * @throws ParameterException
	 * @throws BusinessException
	 */
	public UserDTO register(String phonenumber, String password, String face_url, String ride_read_id, String username)
			throws ParameterException, BusinessException;

	/**
	 * 用户骑阅号校验
	 * 
	 * @param username
	 * @param timestamp
	 * @return boolean
	 * @throws ParameterException
	 * @throws BusinessException
	 */
	public boolean verify(String username, Long timestamp) throws ParameterException, BusinessException;

	/**
	 * 更新用户个人信息
	 * 
	 * @param career
	 * @param phonenumber
	 * @param location
	 * @param birthday
	 * @param face_url
	 * @param uid
	 * @param token
	 * @param signature
	 * @param nickname
	 * @param school
	 * @param sex
	 * @param latitude
	 * @param longitude
	 * @param hometown
	 * @param timestamp
	 * @return UserDTO
	 * @throws ParameterException
	 * @throws BusinessException
	 */
	public UserDTO updateUserInfo(String career, String phonenumber, String location, String birthday, String username,
			String face_url, Integer uid, String token, String signature, String school, Integer sex,
			BigDecimal latitude, BigDecimal longitude, String hometown, String[] tags, Long timestamp)
			throws ParameterException, BusinessException;

	public List<SimplifyUserDTO> findMoreUser(Integer uid, String token, Long timestamp, String userIds)
			throws ParameterException, BusinessException;
}
