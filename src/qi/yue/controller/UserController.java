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
import qi.yue.exception.BusinessException;
import qi.yue.exception.ParameterException;
import qi.yue.service.FollowerService;
import qi.yue.service.FollowingService;
import qi.yue.service.UserService;
import qi.yue.utils.CommonUtil;
import qi.yue.utils.DateUtil;
import qi.yue.utils.EncryptionUtil;
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

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody ResponseDTO login(String phonenumber, String password, BigDecimal latitude,
			BigDecimal longitude) {
		try {
			UserDTO userDTO = userService.login(phonenumber, password, latitude, longitude);
			return ResponseUtil.ConvertToSuccessResponse(userDTO);

		} catch (ParameterException e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER);
		} catch (BusinessException e) {
			return ResponseUtil.ConvertToFailResponse(e.getCode(), e.getMessage());
		} catch (Exception e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL, MessageCommon.FAIL_MESSAGE);
		}

	}

	@RequestMapping(value = "/reset_password", method = RequestMethod.POST)
	public @ResponseBody ResponseDTO resetPassword(String phonenumber, String new_password, Long timestamp) {
		try {

			userService.resetPassword(phonenumber, new_password, timestamp);
			return ResponseUtil.ConvertToSuccessResponse();

		} catch (ParameterException e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER);
		} catch (BusinessException e) {
			return ResponseUtil.ConvertToFailResponse(e.getCode(), e.getMessage());
		} catch (Exception e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL, MessageCommon.FAIL_MESSAGE);
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, headers = "Accept=application/json")
	@Transactional
	public @ResponseBody ResponseDTO register(String phonenumber, String password, String face_url, String ride_read_id,
			String username) {
		try {
			UserDTO userDTO = userService.register(phonenumber, password, face_url, ride_read_id, username);
			return ResponseUtil.ConvertToSuccessResponse(userDTO);

		} catch (ParameterException e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER);
		} catch (BusinessException e) {
			return ResponseUtil.ConvertToFailResponse(e.getCode(), e.getMessage());
		} catch (Exception e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL, MessageCommon.FAIL_MESSAGE);
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
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER);
		} catch (BusinessException e) {
			return ResponseUtil.ConvertToFailResponse(e.getCode(), e.getMessage());
		} catch (Exception e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL, MessageCommon.FAIL_MESSAGE);
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody ResponseDTO update(String career, String phonenumber, String location, String birthday,
			String face_url, Integer uid, String token, String signature, String school, Integer sex,
			BigDecimal latitude, BigDecimal longitude, String hometown, Long timestamp) {
		try {
			UserDTO userDTO = userService.updateUserInfo(career, phonenumber, location, birthday, face_url, uid, token,
					signature, school, sex, latitude, longitude, hometown, timestamp);
			return ResponseUtil.ConvertToSuccessResponse(userDTO);

		} catch (ParameterException e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER);
		} catch (BusinessException e) {
			return ResponseUtil.ConvertToFailResponse(e.getCode(), e.getMessage());
		} catch (Exception e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL, MessageCommon.FAIL_MESSAGE);
		}
	}

	@RequestMapping(value = "/followers", method = RequestMethod.POST)
	public @ResponseBody ResponseDTO followers(Integer uid, String token, Long timestamp) {
		try {

			List<FollowerDTO> followerList = followerService.queryFollower(uid, token, timestamp);
			return ResponseUtil.ConvertToSuccessResponse(followerList);

		} catch (ParameterException e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER);
		} catch (BusinessException e) {
			return ResponseUtil.ConvertToFailResponse(e.getCode(), e.getMessage());
		} catch (Exception e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL, MessageCommon.FAIL_MESSAGE);
		}
	}

	@RequestMapping(value = "/followings", method = RequestMethod.POST)
	public @ResponseBody ResponseDTO followings(Integer uid, String token, Long timestamp) {
		try {

			List<FollowingDTO> followingList = followingService.queryFollowing(uid, token, timestamp);
			return ResponseUtil.ConvertToSuccessResponse(followingList);

		} catch (ParameterException e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER);
		} catch (BusinessException e) {
			return ResponseUtil.ConvertToFailResponse(e.getCode(), e.getMessage());
		} catch (Exception e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL, MessageCommon.FAIL_MESSAGE);
		}

	}

	@RequestMapping(value = "/follow", method = RequestMethod.POST)
	public @ResponseBody ResponseDTO follow(Integer uid, String token, Integer user_id, Long timestamp) {
		try {

			followingService.follow(uid, token, user_id, timestamp);
			return ResponseUtil.ConvertToSuccessResponse();

		} catch (ParameterException e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER);
		} catch (BusinessException e) {
			return ResponseUtil.ConvertToFailResponse(e.getCode(), e.getMessage());
		} catch (Exception e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL, MessageCommon.FAIL_MESSAGE);
		}
	}

	@RequestMapping(value = "/unfollow", method = RequestMethod.POST)
	public @ResponseBody ResponseDTO unfollow(Integer uid, String token, Integer user_id, Long timestamp) {
		try {

			followingService.unfollow(uid, token, user_id, timestamp);
			return ResponseUtil.ConvertToSuccessResponse();

		} catch (ParameterException e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER);
		} catch (BusinessException e) {
			return ResponseUtil.ConvertToFailResponse(e.getCode(), e.getMessage());
		} catch (Exception e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL, MessageCommon.FAIL_MESSAGE);
		}
	}

	@RequestMapping(value = "/show_self", method = RequestMethod.POST)
	public @ResponseBody Object showSelf(Integer uid, String token, Long timestamp) {
		try {
			if (CommonUtil.isNull(uid) || CommonUtil.isNullOrEmpty(token) || CommonUtil.isNullOrEmpty(timestamp)) {
				throw new ParameterException();
			}
			UserDTO user = userService.find(uid);
			return ResponseUtil.ConvertToSuccessResponse(user);

		} catch (ParameterException e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER);
		} catch (BusinessException e) {
			return ResponseUtil.ConvertToFailResponse(e.getCode(), e.getMessage());
		} catch (Exception e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL, MessageCommon.FAIL_MESSAGE);
		}
	}
}