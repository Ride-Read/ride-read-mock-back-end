package qi.yue.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import qi.yue.entity.User;
import qi.yue.service.UserService;
import qi.yue.utils.CommonUtil;
import qi.yue.utils.MD5Util;

@Controller
@RequestMapping("/users")
public class UserController {

	@Resource
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Map<String, Object> login(String username, String password) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (CommonUtil.isNullOrEmpty(username) || CommonUtil.isNullOrEmpty(password)) {
			result.put("data", "");
			result.put("status", 1);
		} else {
			User user = userService.findByUsername(username);
			if (CommonUtil.isNull(user)) {
				result.put("data", "");
				result.put("status", 1002);
			} else {
				if (!password.equals(user.getPassword())) {
					result.put("data", "");
					result.put("status", 1003);
				} else {
					Long timestamp = new Date().getTime();
					String key = "airing";
					String token = MD5Util.GetMD5Code(user.getUid() + timestamp + key);
					user.setToken(token);
					userService.save(user);
					result.put("data", user);
					result.put("status", 0);
				}
			}
		}
		return result;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Map<String, Object> register(String phonenumber, String password, String face_url,
			String nickname) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (CommonUtil.isNullOrEmpty(phonenumber) || CommonUtil.isNullOrEmpty(password)
				|| CommonUtil.isNullOrEmpty(face_url) || CommonUtil.isNullOrEmpty(nickname)) {
			// result.put("data", "");
			result.put("status", 1);
		} else {
			User usered = userService.findByUsername(phonenumber);
			if (!CommonUtil.isNull(usered)) {
				result.put("status", 1000);
			} else {
				User user = new User();
				user.setPhonenumber(phonenumber);
				user.setPassword(password);
				user.setFaceUrl(face_url);
				user.setNickname(nickname);
				userService.save(user);
				result.put("data", user);
				result.put("status", 0);
			}
		}
		return result;
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
	public @ResponseBody Map<String, Object> update(String career, String phonenumber, String location, String birthday,
			String face_url, Integer uid, String token, String signature, String nickname, String school, Integer sex,
			String hometown, Long timestamp) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (CommonUtil.isNullOrEmpty(career) || CommonUtil.isNullOrEmpty(phonenumber)
				|| CommonUtil.isNullOrEmpty(location) || CommonUtil.isNullOrEmpty(birthday)
				|| CommonUtil.isNullOrEmpty(face_url) || CommonUtil.isNull(uid) || CommonUtil.isNullOrEmpty(token)
				|| CommonUtil.isNullOrEmpty(signature) || CommonUtil.isNullOrEmpty(nickname)
				|| CommonUtil.isNullOrEmpty(school) || CommonUtil.isNull(sex) || CommonUtil.isNullOrEmpty(hometown)
				|| CommonUtil.isNull(timestamp)) {
			// result.put("data", "");
			result.put("status", 1);
		} else {
			User usered = userService.findByUsername(phonenumber);
			if (!CommonUtil.isNull(usered)) {
				result.put("status", 1000);
			} else {
				User user = new User();
				user.setCareer(career);
				user.setPhonenumber(phonenumber);
				user.setLocation(location);
				user.setBirthday(new Date(birthday));
				user.setFaceUrl(face_url);
				user.setUid(uid);
				user.setToken(token);
				user.setSignature(signature);
				user.setNickname(nickname);
				user.setSchool(school);
				user.setSex(sex);
				user.setHometown(hometown);
				userService.update(user);
				result.put("data", user);
				result.put("status", 0);
			}
		}
		return result;
	}

	@RequestMapping(value = "/followings", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> followings(Integer uid, String token, Long timestamp) {

		Map<String, Object> result = new HashMap<String, Object>();
		if (CommonUtil.isNull(uid) || CommonUtil.isNullOrEmpty(token)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("uid", uid);
			map.put("token", token);
			// User usered = userService.findByIdAndToken(map);
			// if (CommonUtil.isNull(usered)) {
			// result.put("status", 1);
			// } else {
			//
			// }

		}
		return result;
	}

}