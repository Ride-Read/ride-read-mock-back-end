package qi.yue.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import qi.yue.common.MessageCommon;
import qi.yue.dto.FollowerDTO;
import qi.yue.dto.FollowingDTO;
import qi.yue.dto.ResponseDTO;
import qi.yue.dto.UserDTO;
import qi.yue.dto.assembler.UserDtoAssembler;
import qi.yue.entity.Follower;
import qi.yue.entity.Following;
import qi.yue.entity.User;
import qi.yue.service.FollowerService;
import qi.yue.service.FollowingService;
import qi.yue.service.UserService;
import qi.yue.utils.CommonUtil;
import qi.yue.utils.DateUtil;
import qi.yue.utils.EncryptionUtil;

@Controller
@RequestMapping("/users")
public class UserController {

	@Resource
	private UserService userService;
	@Resource
	private FollowingService followingService;
	@Resource
	private FollowerService followerService;

	// @RequestMapping("/toAdd.action")
	// public String toAdd(User user) {
	// int i = 1;
	// System.out.println(i);
	// return "addUser";
	// }
	//
	// @RequestMapping("/save.action")
	// @Transactional
	// public String save(User user) {
	// // userService.add(user);
	// return "addUser";
	// }

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody Object login(String phonenumber, String password, BigDecimal latitude, BigDecimal longitude) {
		ResponseDTO responseDTO = new ResponseDTO();
		if (CommonUtil.isNullOrEmpty(phonenumber) || CommonUtil.isNullOrEmpty(password) || CommonUtil.isNull(latitude)
				|| CommonUtil.isNull(longitude)) {
			responseDTO.setStatus(MessageCommon.STATUS_FAIL);
			return responseDTO;
		}
		UserDTO dto = userService.findByPhonenumber(phonenumber);
		if (CommonUtil.isNull(dto)) {
			responseDTO.setStatus(MessageCommon.STATUS_USER_NOT_EXIST);
		} else {
			password = EncryptionUtil.GetSHACode(password);
			if (!password.equals(dto.getPassword())) {
				responseDTO.setStatus(MessageCommon.STATUS_PASSWORD_WRONG);
			} else {
				long timestamp = new Date().getTime();
				String token = EncryptionUtil.GetMD5Code(dto.getUid() + timestamp + MessageCommon.PUBLIC_KEY);
				dto.setToken(token);
				dto.setLatitude(latitude);
				dto.setLongitude(longitude);
				if (!CommonUtil.isNullOrEmpty(dto.getTagString())) {
					dto.setTags(dto.getTagString().split(","));
				}
				User user = new User();
				user.setId(dto.getUid());
				user.setToken(token);
				user.setLatitude(latitude);
				user.setLongitude(longitude);

				userService.update(user);
				responseDTO.setTimestamp(timestamp);
				responseDTO.setData(dto);
				responseDTO.setStatus(MessageCommon.STATUS_SUCCESS);
			}
		}
		return responseDTO;
	}

	@RequestMapping(value = "/reset_password", method = RequestMethod.POST)
	public @ResponseBody Object resetPassword(String phonenumber, String new_password, Long timestamp) {
		ResponseDTO responseDTO = new ResponseDTO();
		if (CommonUtil.isNullOrEmpty(phonenumber) || CommonUtil.isNullOrEmpty(new_password)
				|| CommonUtil.isNullOrEmpty(timestamp)) {
			responseDTO.setStatus(MessageCommon.STATUS_FAIL);
			return responseDTO;
		}
		UserDTO dto = userService.findByPhonenumber(phonenumber);
		if (CommonUtil.isNull(dto)) {
			responseDTO.setStatus(MessageCommon.STATUS_USER_NOT_EXIST);
		} else {
			String passwordSHA = EncryptionUtil.GetSHACode(new_password);
			userService.updatePasswordByPhonenumber(passwordSHA, phonenumber);
			responseDTO.setStatus(MessageCommon.STATUS_SUCCESS);
		}
		return responseDTO;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, headers = "Accept=application/json")
	@Transactional
	public @ResponseBody Object register(String phonenumber, String password, String face_url, String nickname,
			String username) {
		ResponseDTO responseDTO = new ResponseDTO();
		if (CommonUtil.isNullOrEmpty(phonenumber) || CommonUtil.isNullOrEmpty(password)
				|| CommonUtil.isNullOrEmpty(face_url) || CommonUtil.isNullOrEmpty(nickname)
				|| CommonUtil.isNullOrEmpty(username)) {
			responseDTO.setStatus(MessageCommon.STATUS_FAIL);
			return responseDTO;
		}
		UserDTO userName = userService.findByUsername(username);
		UserDTO userPN = userService.findByPhonenumber(phonenumber);
		if (!CommonUtil.isNull(userName) || !CommonUtil.isNull(userPN)) {
			if (!CommonUtil.isNull(userName)) {
				responseDTO.setStatus(MessageCommon.STATUS_USERNAME_EXIST);
			} else {
				responseDTO.setStatus(MessageCommon.STATUS_PHONENUMBER_EXIST);
			}
		} else {
			String passwordSHA = EncryptionUtil.GetSHACode(password);
			User user = new User();
			user.setUsername(username);
			user.setPhonenumber(phonenumber);
			user.setPassword(passwordSHA);
			user.setFaceUrl(face_url);
			user.setNickname(nickname);

			Date date = new Date();
			user.setCreatedAt(date);
			user.setUpdatedAt(date);
			userService.save(user);

			long timestamp = date.getTime();
			String token = EncryptionUtil.GetMD5Code(user.getId() + timestamp + MessageCommon.PUBLIC_KEY);
			user.setToken(token);
			userService.updateTokenById(user.getToken(), user.getId());
			responseDTO.setTimestamp(timestamp);
			responseDTO.setData(UserDtoAssembler.toDto(user));
			responseDTO.setStatus(MessageCommon.STATUS_SUCCESS);
		}
		return responseDTO;
	}

	@RequestMapping(value = "/verify_code", method = RequestMethod.POST)
	public @ResponseBody Object verify_code(String phonenumber, Long timestamp) {
		ResponseDTO responseDTO = new ResponseDTO();
		if (CommonUtil.isNullOrEmpty(phonenumber) || CommonUtil.isNull(timestamp)) {
			responseDTO.setStatus(MessageCommon.STATUS_FAIL);
			return responseDTO;
		}
		UserDTO dto = userService.findByPhonenumber(phonenumber);
		if (CommonUtil.isNull(dto)) {
			responseDTO.setStatus(MessageCommon.STATUS_USER_NOT_EXIST);
		} else {
			responseDTO.setStatus(MessageCommon.STATUS_SUCCESS);
		}
		return responseDTO;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody Object update(String career, String phonenumber, String location, String birthday,
			String face_url, Integer uid, String token, String signature, String nickname, String school, Integer sex,
			BigDecimal latitude, BigDecimal longitude, String hometown, Long timestamp) {
		ResponseDTO responseDTO = new ResponseDTO();
		if (CommonUtil.isNullOrEmpty(career) || CommonUtil.isNullOrEmpty(phonenumber)
				|| CommonUtil.isNullOrEmpty(location) || CommonUtil.isNullOrEmpty(birthday)
				|| CommonUtil.isNullOrEmpty(face_url) || CommonUtil.isNull(uid) || CommonUtil.isNullOrEmpty(token)
				|| CommonUtil.isNullOrEmpty(signature) || CommonUtil.isNullOrEmpty(nickname)
				|| CommonUtil.isNullOrEmpty(school) || CommonUtil.isNull(sex) || CommonUtil.isNull(latitude)
				|| CommonUtil.isNull(longitude) || CommonUtil.isNullOrEmpty(hometown) || CommonUtil.isNull(timestamp)) {
			responseDTO.setStatus(MessageCommon.STATUS_FAIL);
			return responseDTO;
		}
		// String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp +
		// MessageCommon.PUBLIC_KEY);
		// if (!tokenTemp.equals(token)) {
		// responseDTO.setStatus(MessageCommon.STATUS_FAIL);
		// } else {
		User user = new User();
		user.setId(uid);
		user.setCareer(career);
		user.setPhonenumber(phonenumber);
		user.setLocation(location);
		try {
			user.setBirthday(DateUtil.strToDate(birthday, MessageCommon.DATE_TIME_FORMAT));
		} catch (ParseException e) {
			responseDTO.setStatus(MessageCommon.STATUS_FAIL);
			return responseDTO;
		}
		user.setFaceUrl(face_url);
		user.setId(uid);
		user.setToken(token);
		user.setSignature(signature);
		user.setNickname(nickname);
		user.setSchool(school);
		user.setLatitude(latitude);
		user.setLongitude(longitude);
		user.setSex(sex);
		user.setHometown(hometown);
		user.setUpdatedAt(new Date());
		userService.update(user);
		// UserDTO parameterUser = UserDtoAssembler.toDto(user);
		UserDTO dto = userService.findByPhonenumber(phonenumber);
		if (!CommonUtil.isNullOrEmpty(dto.getTagString())) {
			dto.setTags(dto.getTagString().split(","));
		}
		responseDTO.setData(dto);
		responseDTO.setStatus(MessageCommon.STATUS_SUCCESS);
		// }
		return responseDTO;
	}

	@RequestMapping(value = "/followers", method = RequestMethod.POST)
	public @ResponseBody Object followers(Integer uid, String token, Long timestamp) {
		ResponseDTO responseDTO = new ResponseDTO();
		if (CommonUtil.isNull(uid) || CommonUtil.isNullOrEmpty(token) || CommonUtil.isNullOrEmpty(timestamp)) {
			responseDTO.setStatus(MessageCommon.STATUS_FAIL);
			return responseDTO;
		}
		// String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp +
		// MessageCommon.PUBLIC_KEY);
		// if (!tokenTemp.equals(token)) {
		// responseDTO.setStatus(MessageCommon.STATUS_FAIL);
		// } else {
		List<FollowerDTO> dtoList = followerService.findByTid(uid);
		responseDTO.setData(dtoList);
		responseDTO.setStatus(MessageCommon.STATUS_SUCCESS);
		// }
		return responseDTO;
	}

	@RequestMapping(value = "/followings", method = RequestMethod.POST)
	public @ResponseBody Object followings(Integer uid, String token, Long timestamp) {
		ResponseDTO responseDTO = new ResponseDTO();
		if (CommonUtil.isNull(uid) || CommonUtil.isNullOrEmpty(token) || CommonUtil.isNullOrEmpty(timestamp)) {
			responseDTO.setStatus(MessageCommon.STATUS_FAIL);
			return responseDTO;
		}
		// String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp +
		// MessageCommon.PUBLIC_KEY);
		// if (!tokenTemp.equals(token)) {
		// responseDTO.setStatus(MessageCommon.STATUS_FAIL);
		// } else {
		List<FollowingDTO> dtoList = followingService.findByFid(uid);
		responseDTO.setData(dtoList);
		responseDTO.setStatus(MessageCommon.STATUS_SUCCESS);
		// }
		return responseDTO;
	}

	@RequestMapping(value = "/follow", method = RequestMethod.POST)
	public @ResponseBody Object follow(Integer uid, String token, Integer user_id, Long timestamp) {
		ResponseDTO responseDTO = new ResponseDTO();
		if (CommonUtil.isNull(uid) || CommonUtil.isNullOrEmpty(token) || CommonUtil.isNullOrEmpty(user_id)
				|| CommonUtil.isNullOrEmpty(timestamp)) {
			responseDTO.setStatus(MessageCommon.STATUS_FAIL);
			return responseDTO;
		}
		// String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp +
		// MessageCommon.PUBLIC_KEY);
		// if (!tokenTemp.equals(token)) {
		// responseDTO.setStatus(MessageCommon.STATUS_FAIL);
		// } else {
		UserDTO userFollowed = userService.find(user_id);
		UserDTO userFollowing = userService.find(uid);
		if (CommonUtil.isNull(userFollowed) || CommonUtil.isNull(userFollowing)) {
			responseDTO.setStatus(MessageCommon.STATUS_FAIL);
		} else {
			Following following = new Following();
			following.setFid(uid);
			following.setTid(user_id);
			following.setFaceUrl(userFollowed.getFace_url());
			following.setSignature(userFollowed.getSignature());
			following.setNickname(userFollowed.getNickname());
			following.setCreatedAt(new Date());
			following.setUpdatedAt(new Date());

			Follower follower = new Follower();
			follower.setFid(uid);
			follower.setTid(user_id);
			follower.setFaceUrl(userFollowing.getFace_url());
			follower.setSignature(userFollowing.getSignature());
			follower.setNickname(userFollowing.getNickname());
			follower.setCreatedAt(new Date());
			follower.setUpdatedAt(new Date());

			followingService.saveFollowingAndFollower(following, follower);
			responseDTO.setStatus(MessageCommon.STATUS_SUCCESS);
		}
		// }
		return responseDTO;
	}

	@RequestMapping(value = "/show_self", method = RequestMethod.POST)
	public @ResponseBody Object showSelf(Integer uid, String token, Long timestamp) {
		ResponseDTO responseDTO = new ResponseDTO();
		if (CommonUtil.isNull(uid) || CommonUtil.isNullOrEmpty(token) || CommonUtil.isNullOrEmpty(timestamp)) {
			responseDTO.setStatus(MessageCommon.STATUS_FAIL);
			return responseDTO;
		}
		// String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp +
		// MessageCommon.PUBLIC_KEY);
		// if (!tokenTemp.equals(token)) {
		// responseDTO.setStatus(MessageCommon.STATUS_FAIL);
		// } else {
		UserDTO user = userService.find(uid);
		responseDTO.setData(user);
		responseDTO.setStatus(MessageCommon.STATUS_SUCCESS);
		// }
		return responseDTO;
	}

	@RequestMapping(value = "/unfollow", method = RequestMethod.POST)
	public @ResponseBody Object unfollow(Integer uid, String token, Integer user_id, Long timestamp) {
		ResponseDTO responseDTO = new ResponseDTO();
		if (CommonUtil.isNull(uid) || CommonUtil.isNullOrEmpty(token) || CommonUtil.isNull(user_id)
				|| CommonUtil.isNullOrEmpty(timestamp)) {
			responseDTO.setStatus(MessageCommon.STATUS_FAIL);
			return responseDTO;
		}
		// String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp +
		// MessageCommon.PUBLIC_KEY);
		// if (!tokenTemp.equals(token)) {
		// responseDTO.setStatus(MessageCommon.STATUS_FAIL);
		// } else {
		followingService.deleteFollowingAndFollower(uid, user_id);
		responseDTO.setStatus(MessageCommon.STATUS_SUCCESS);
		// }
		return responseDTO;
	}
}