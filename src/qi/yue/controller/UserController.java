package qi.yue.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import qi.yue.common.MessageCommon;
import qi.yue.dto.FollowDTO;
import qi.yue.dto.ResponseDTO;
import qi.yue.dto.UserDTO;
import qi.yue.exception.BusinessException;
import qi.yue.exception.ParameterException;
import qi.yue.service.FollowService;
import qi.yue.service.FollowerService;
import qi.yue.service.FollowingService;
import qi.yue.service.UserService;
import qi.yue.utils.CommonUtil;
import qi.yue.utils.ResponseUtil;

@Controller
@RequestMapping("/users")
public class UserController {
	@Resource
	private UserService userService;
	@Resource
	private FollowingService followingService;
	@Resource
	private FollowerService followerService;
	@Resource
	private FollowService followService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody ResponseDTO login(String phonenumber, String password, BigDecimal latitude,
			BigDecimal longitude) {
		Long timestamp = new Date().getTime();
		try {
			UserDTO userDTO = userService.login(phonenumber, password, latitude, longitude, timestamp);
			return ResponseUtil.ConvertToSuccessResponse(userDTO, timestamp);
		} catch (ParameterException e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER, timestamp);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(e.getCode(), e.getMessage(), timestamp);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL, MessageCommon.FAIL_MESSAGE, timestamp);
		}

	}

	@RequestMapping(value = "/reset_password", method = RequestMethod.POST)
	public @ResponseBody ResponseDTO resetPassword(String phonenumber, String new_password, Long timestamp) {
		try {
			userService.resetPassword(phonenumber, new_password, timestamp);
			return ResponseUtil.ConvertToSuccessResponse();
		} catch (ParameterException e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL, MessageCommon.FAIL_MESSAGE);
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody ResponseDTO register(String phonenumber, String password, String face_url, String ride_read_id,
			String username) {
		Long timestamp = new Date().getTime();
		try {
			UserDTO userDTO = userService.register(phonenumber, password, face_url, ride_read_id, username);
			return ResponseUtil.ConvertToSuccessResponse(userDTO, timestamp);

		} catch (ParameterException e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER, timestamp);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(e.getCode(), e.getMessage(), timestamp);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL, MessageCommon.FAIL_MESSAGE, timestamp);
		}
	}

	@RequestMapping(value = "/verify", method = RequestMethod.POST)
	public @ResponseBody ResponseDTO verify(String ride_read_id, Long timestamp) {
		// TODO not completed. It should be verified By qiyueID, not username;
		try {

			boolean result = userService.verify(ride_read_id, timestamp);
			if (result) {
				return ResponseUtil.ConvertToSuccessResponse();
			} else {
				return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_USER_NOT_EXIST,
						MessageCommon.FAIL_MESSAGE_USER_NOT_EXIST);
			}
		} catch (ParameterException e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL, MessageCommon.FAIL_MESSAGE);
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody ResponseDTO update(String career, String phonenumber, String location, String birthday,
			String username, String face_url, Integer uid, String token, String signature, String school, Integer sex,
			BigDecimal latitude, BigDecimal longitude, String hometown, Long timestamp) {
		try {
			UserDTO userDTO = userService.updateUserInfo(career, phonenumber, location, birthday, username, face_url,
					uid, token, signature, school, sex, latitude, longitude, hometown, timestamp);
			return ResponseUtil.ConvertToSuccessResponse(userDTO, timestamp);

		} catch (ParameterException e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER, timestamp);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(e.getCode(), e.getMessage(), timestamp);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL, MessageCommon.FAIL_MESSAGE, timestamp);
		}
	}

	@RequestMapping(value = "/followers", method = RequestMethod.POST)
	public @ResponseBody ResponseDTO followers(Integer uid, String token, Long timestamp) {
		try {

			List<FollowDTO> followerList = followService.queryFollower(uid, token, timestamp);
			return ResponseUtil.ConvertToSuccessResponse(followerList);

		} catch (ParameterException e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL, MessageCommon.FAIL_MESSAGE);
		}
	}

	@RequestMapping(value = "/followings", method = RequestMethod.POST)
	public @ResponseBody ResponseDTO followings(Integer uid, String token, Long timestamp) {
		try {
			List<FollowDTO> followingList = followService.queryFollowed(uid, token, timestamp);
			return ResponseUtil.ConvertToSuccessResponse(followingList);
		} catch (ParameterException e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL, MessageCommon.FAIL_MESSAGE);
		}
	}

	@RequestMapping(value = "/follow", method = RequestMethod.POST)
	public @ResponseBody ResponseDTO follow(Integer uid, String token, Integer user_id, Long timestamp) {
		try {
			followService.follow(uid, token, user_id, timestamp);
			return ResponseUtil.ConvertToSuccessResponse();
		} catch (ParameterException e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER, timestamp);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(e.getCode(), e.getMessage(), timestamp);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL, MessageCommon.FAIL_MESSAGE, timestamp);
		}
	}

	@RequestMapping(value = "/unfollow", method = RequestMethod.POST)
	public @ResponseBody ResponseDTO unfollow(Integer uid, String token, Integer user_id, Long timestamp) {
		try {
			followService.unfollow(uid, token, user_id, timestamp);
			return ResponseUtil.ConvertToSuccessResponse();
		} catch (ParameterException e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL, MessageCommon.FAIL_MESSAGE);
		}
	}

	@RequestMapping(value = "/show_user_info", method = RequestMethod.POST)
	public @ResponseBody Object showUserInfo(Integer uid, String token, Long timestamp, Integer user_id, Integer type) {
		try {
			if (CommonUtil.isNull(uid) || CommonUtil.isNullOrEmpty(token) || CommonUtil.isNullOrEmpty(timestamp)
					|| CommonUtil.isNullOrEmpty(type)) {
				throw new ParameterException();
			}
			UserDTO user = null;
			if (1 == type) {
				user = userService.find(uid);
			} else if (2 == type) {
				if (CommonUtil.isNullOrEmpty(user_id)) {
					throw new ParameterException();
				}
				user = userService.find(user_id);
			}
			return ResponseUtil.ConvertToSuccessResponse(user);
		} catch (ParameterException e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL, MessageCommon.FAIL_MESSAGE);
		}
	}

	@RequestMapping(value = "/search_follower_or_following", method = RequestMethod.POST)
	public @ResponseBody Object searchFollowerOrFollowing(Integer uid, String token, Long timestamp, String shortname) {
		try {

			Map<String, Object> data = followService.searchFollowerOrFollowing(uid, token, timestamp, shortname);
			return ResponseUtil.ConvertToSuccessResponse(data);

		} catch (ParameterException e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL, MessageCommon.FAIL_MESSAGE);
		}
	}

	@RequestMapping(value = "/show_user_info_list", method = RequestMethod.POST)
	public @ResponseBody Object showUserInfoList(Integer uid, String token, Long timestamp, String user_ids) {
		try {
			List<UserDTO> data = userService.findMoreUser(uid, token, timestamp, user_ids);
			return ResponseUtil.ConvertToSuccessResponse(data);

		} catch (ParameterException e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL, MessageCommon.FAIL_MESSAGE);
		}
	}
}