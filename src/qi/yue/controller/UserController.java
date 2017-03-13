package qi.yue.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import qi.yue.common.DateJsonValueProcessor;
import qi.yue.common.MessageCommon;
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
	public @ResponseBody Object login(String username, String password) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (CommonUtil.isNullOrEmpty(username) || CommonUtil.isNullOrEmpty(password)) {
			result.put("data", "");
			result.put("status", MessageCommon.STATUS_FAIL);
		} else {
			User user = userService.findByUsername(username);
			if (CommonUtil.isNull(user)) {
				result.put("data", "");
				result.put("status", MessageCommon.STATUS_USER_NOT_EXIST);
			} else {
				password = EncryptionUtil.GetSHACode(password);
				if (!password.equals(user.getPassword())) {
					result.put("data", "");
					result.put("status", MessageCommon.STATUS_PASSWORD_WRONG);
				} else {
					long timestamp = new Date().getTime();
					String token = EncryptionUtil.GetMD5Code(user.getId() + timestamp + MessageCommon.PUBLIC_KEY);
					user.setToken(token);
					userService.updateTokenById(user.getToken(), user.getId());
					result.put("timestamp", timestamp);
					result.put("data", user);
					result.put("status", MessageCommon.STATUS_SUCCESS);
				}
			}
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor());
		return JSONObject.fromObject(result, jsonConfig);
	}

	@RequestMapping(value = "/reset_password", method = RequestMethod.POST)
	public @ResponseBody Object resetPassword(String username, String new_password, Long timestamp) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (CommonUtil.isNullOrEmpty(username) || CommonUtil.isNullOrEmpty(new_password)
				|| CommonUtil.isNullOrEmpty(timestamp)) {
			result.put("data", "");
			result.put("status", MessageCommon.STATUS_FAIL);
		} else {
			User user = userService.findByUsername(username);
			if (CommonUtil.isNull(user)) {
				result.put("data", "");
				result.put("status", MessageCommon.STATUS_USER_NOT_EXIST);
			} else {
				String passwordSHA = EncryptionUtil.GetSHACode(new_password);
				userService.updatePasswordByUsernamae(passwordSHA, username);
				result.put("status", MessageCommon.STATUS_SUCCESS);
			}
		}
		return result;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, headers = "Accept=application/json")
	@Transactional
	public @ResponseBody Object register(String phonenumber, String password, String face_url, String nickname) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (CommonUtil.isNullOrEmpty(phonenumber) || CommonUtil.isNullOrEmpty(password)
				|| CommonUtil.isNullOrEmpty(face_url) || CommonUtil.isNullOrEmpty(nickname)) {
			result.put("data", "");
			result.put("status", MessageCommon.STATUS_FAIL);
		} else {
			User usered = userService.findByUsername(phonenumber);
			if (!CommonUtil.isNull(usered)) {
				result.put("data", "");
				result.put("status", MessageCommon.STATUS_USER_EXIST);
			} else {
				String passwordSHA = EncryptionUtil.GetSHACode(password);
				User user = new User();
				user.setUsername(phonenumber);
				user.setPhonenumber(phonenumber);
				user.setPassword(passwordSHA);
				user.setFaceUrl(face_url);
				user.setNickname(nickname);
				user.setCreatedAt(new Date());
				user.setUpdatedAt(new Date());
				userService.save(user);

				long timestamp = new Date().getTime();
				String token = EncryptionUtil.GetMD5Code(user.getId() + timestamp + MessageCommon.PUBLIC_KEY);
				user.setToken(token);
				userService.updateTokenById(user.getToken(), user.getId());
				result.put("timestamp", timestamp);
				result.put("data", userService.findByUsername(phonenumber));
				result.put("status", MessageCommon.STATUS_SUCCESS);
			}
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor());
		return JSONObject.fromObject(result, jsonConfig);
	}

	@RequestMapping(value = "/verify_code", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> verify_code(@RequestParam("code") String code) {

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("token", "token001");
		result.put("status", "0");
		result.put("phonenumber", "135791113");
		return result;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody Object update(String career, String phonenumber, String location, String birthday,
			String face_url, Integer uid, String token, String signature, String nickname, String school, Integer sex,
			String hometown, Long timestamp) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (CommonUtil.isNullOrEmpty(career) || CommonUtil.isNullOrEmpty(phonenumber)
				|| CommonUtil.isNullOrEmpty(location) || CommonUtil.isNullOrEmpty(birthday)
				|| CommonUtil.isNullOrEmpty(face_url) || CommonUtil.isNull(uid) || CommonUtil.isNullOrEmpty(token)
				|| CommonUtil.isNullOrEmpty(signature) || CommonUtil.isNullOrEmpty(nickname)
				|| CommonUtil.isNullOrEmpty(school) || CommonUtil.isNull(sex) || CommonUtil.isNullOrEmpty(hometown)
				|| CommonUtil.isNull(timestamp)) {
			result.put("data", "");
			result.put("status", MessageCommon.STATUS_FAIL);
		} else {
			String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp + MessageCommon.PUBLIC_KEY);
			if (!tokenTemp.equals(token)) {
				result.put("data", "");
				result.put("status", MessageCommon.STATUS_FAIL);
			} else {
				User user = new User();
				user.setId(uid);
				user.setCareer(career);
				user.setPhonenumber(phonenumber);
				user.setLocation(location);
				try {
					user.setBirthday(DateUtil.strToDate(birthday, MessageCommon.DATE_TIME_FORMAT));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				user.setFaceUrl(face_url);
				user.setId(uid);
				user.setToken(token);
				user.setSignature(signature);
				user.setNickname(nickname);
				user.setSchool(school);
				user.setSex(sex);
				user.setHometown(hometown);
				user.setUpdatedAt(new Date());
				userService.update(user);
				User parameterUser = userService.findByUsername(phonenumber);
				result.put("data", parameterUser);
				result.put("status", MessageCommon.STATUS_SUCCESS);
			}
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor());
		return JSONObject.fromObject(result, jsonConfig);
	}

	@RequestMapping(value = "/followers", method = RequestMethod.POST)
	public @ResponseBody Object followers(Integer uid, String token, Long timestamp) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (CommonUtil.isNull(uid) || CommonUtil.isNullOrEmpty(token) || CommonUtil.isNullOrEmpty(timestamp)) {
			result.put("data", "");
			result.put("status", MessageCommon.STATUS_FAIL);
		} else {
			String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp + MessageCommon.PUBLIC_KEY);
			if (!tokenTemp.equals(token)) {
				result.put("data", "");
				result.put("status", MessageCommon.STATUS_FAIL);
			} else {
				List<Follower> FollowingList = followerService.findByTid(uid);
				result.put("data", FollowingList);
				result.put("status", MessageCommon.STATUS_SUCCESS);
			}
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor());
		return JSONObject.fromObject(result, jsonConfig);
	}

	@RequestMapping(value = "/followings", method = RequestMethod.POST)
	public @ResponseBody Object followings(Integer uid, String token, Long timestamp) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (CommonUtil.isNull(uid) || CommonUtil.isNullOrEmpty(token) || CommonUtil.isNullOrEmpty(timestamp)) {
			result.put("data", "");
			result.put("status", MessageCommon.STATUS_FAIL);
		} else {
			String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp + MessageCommon.PUBLIC_KEY);
			if (!tokenTemp.equals(token)) {
				result.put("data", "");
				result.put("status", MessageCommon.STATUS_FAIL);
			} else {
				List<Following> FollowingList = followingService.findByFid(uid);
				result.put("data", FollowingList);
				result.put("status", MessageCommon.STATUS_SUCCESS);
			}
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor());
		return JSONObject.fromObject(result, jsonConfig);
	}

	@RequestMapping(value = "/follow", method = RequestMethod.POST)
	public @ResponseBody Object follow(Integer uid, String token, Integer user_id, Long timestamp) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (CommonUtil.isNull(uid) || CommonUtil.isNullOrEmpty(token) || CommonUtil.isNullOrEmpty(user_id)
				|| CommonUtil.isNullOrEmpty(timestamp)) {
			result.put("data", "");
			result.put("status", MessageCommon.STATUS_FAIL);
		} else {
			String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp + MessageCommon.PUBLIC_KEY);
			if (!tokenTemp.equals(token)) {
				result.put("data", "");
				result.put("status", MessageCommon.STATUS_FAIL);
			} else {
				User userFollowed = userService.find(user_id);
				User userFollowing = userService.find(uid);
				if (CommonUtil.isNull(userFollowed) || CommonUtil.isNull(userFollowing)) {
					result.put("data", "");
					result.put("status", MessageCommon.STATUS_FAIL);
				} else {
					Following following = new Following();
					following.setFid(uid);
					following.setTid(user_id);
					following.setFaceUrl(userFollowed.getFaceUrl());
					following.setSignature(userFollowed.getSignature());
					following.setNickname(userFollowed.getNickname());
					following.setCreatedAt(new Date());
					following.setUpdatedAt(new Date());

					Follower follower = new Follower();
					follower.setFid(uid);
					follower.setTid(user_id);
					follower.setFaceUrl(userFollowing.getFaceUrl());
					follower.setSignature(userFollowing.getSignature());
					follower.setNickname(userFollowing.getNickname());
					follower.setCreatedAt(new Date());
					follower.setUpdatedAt(new Date());

					followingService.saveFollowingAndFollower(following, follower);
					result.put("status", MessageCommon.STATUS_SUCCESS);
				}
			}
		}
		return result;
	}

	@RequestMapping(value = "/unfollow", method = RequestMethod.POST)
	public @ResponseBody Object unfollow(Integer uid, String token, Integer user_id, Long timestamp) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (CommonUtil.isNull(uid) || CommonUtil.isNullOrEmpty(token) || CommonUtil.isNull(user_id)
				|| CommonUtil.isNullOrEmpty(timestamp)) {
			result.put("data", "");
			result.put("status", MessageCommon.STATUS_FAIL);
		} else {
			String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp + MessageCommon.PUBLIC_KEY);
			if (!tokenTemp.equals(token)) {
				result.put("data", "");
				result.put("status", MessageCommon.STATUS_FAIL);
			} else {
				followingService.deleteFollowingAndFollower(uid, user_id);
				result.put("status", MessageCommon.STATUS_SUCCESS);
			}
		}
		return result;
	}
}