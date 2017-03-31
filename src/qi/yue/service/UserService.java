package qi.yue.service;

import java.math.BigDecimal;

import qi.yue.dto.UserDTO;
import qi.yue.entity.User;
import qi.yue.exception.BusinessException;
import qi.yue.exception.ParameterException;

public interface UserService {
	public UserDTO findByUsernameAndPassword(String username, String password);

	public UserDTO findByUidAndToken(Integer uid, String token);

//	public UserDTO findByUsername(String username);

	/**
	 * 通过电话号码查询用户信息
	 * @param String
	 * @return UserDTO
	 * @throws BusinessException
	 */
//	public UserDTO findByPhonenumber(String phonenumber) throws BusinessException;

	public UserDTO find(Integer id);

	UserDTO findThumbsUpUserByMid(Integer mid);

//	public int save(User user);

	/**
	 * 更新user表
	 * @param user
	 * @return int
	 * @throws ParameterException
	 * @throws BusinessException
	 */
//	public int update(User user) throws ParameterException,BusinessException;

	public int updateTokenById(String token, Integer uid);

	public int updatePasswordByUsernamae(String password, String username);

	/**
	 * 根据电话号码更新用户密码
	 * @param password
	 * @param phonenumber
	 * @return int
	 * @throws ParameterException
	 * @throws BusinessException
	 */
//	public int updatePasswordByPhonenumber(String password, String phonenumber) throws ParameterException, BusinessException;
	
	/**
	 *  用户登录
	 * @param phonenumber
	 * @param password
	 * @param latitude
	 * @param longitude
	 * @return UserDTO
	 * @throws ParameterException
	 * @throws BusinessException
	 */
	public UserDTO login(String phonenumber, String password, BigDecimal latitude, BigDecimal longitude) throws ParameterException, BusinessException;
	
	/**
	 * 重置密码
	 * @param phonenumber
	 * @param new_password
	 * @param timestamp
	 * @throws ParameterException
	 * @throws BusinessException
	 */
	public void resetPassword(String phonenumber, String new_password, Long timestamp) throws ParameterException, BusinessException;
	
	/**
	 * 用户注册
	 * @param phonenumber
	 * @param password
	 * @param face_url
	 * @param nickname
	 * @param username
	 * @return User
	 * @throws ParameterException
	 * @throws BusinessException
	 */
	public UserDTO register(String phonenumber, String password, String face_url, String nickname, String username) throws ParameterException, BusinessException;

	/**
	 * 用户骑阅号校验
	 * @param username
	 * @param timestamp
	 * @return boolean
	 * @throws ParameterException
	 * @throws BusinessException
	 */
	public boolean verify(String username, Long timestamp) throws ParameterException, BusinessException;
	

	/**
	 * 更新用户个人信息
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
	public UserDTO updateUserInfo(String career, String phonenumber, String location, String birthday,
			String face_url, Integer uid, String token, String signature, String nickname, String school, Integer sex,
			BigDecimal latitude, BigDecimal longitude, String hometown, Long timestamp) throws ParameterException, BusinessException;
}
