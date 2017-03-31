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
	public @ResponseBody ResponseDTO login(String phonenumber, String password, BigDecimal latitude, BigDecimal longitude) {
		try{
			
			UserDTO userDTO = userService.login(phonenumber, password, latitude, longitude);
			return ResponseUtil.ConvertToSuccessResponse(userDTO);
			
		}catch(ParameterException e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER);
		}catch(BusinessException e) {
			return ResponseUtil.ConvertToFailResponse(e.getCode(),
					e.getMessage());
		}catch(Exception e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL,
					MessageCommon.FAIL_MESSAGE);
		}
		
	}

	@RequestMapping(value = "/reset_password", method = RequestMethod.POST)
	public @ResponseBody ResponseDTO resetPassword(String phonenumber, String new_password, Long timestamp) {
		try{
			
			userService.resetPassword(phonenumber, new_password, timestamp);
			return ResponseUtil.ConvertToSuccessResponse();
			
		}catch(ParameterException e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER);
		}catch(BusinessException e) {
			return ResponseUtil.ConvertToFailResponse(e.getCode(),
					e.getMessage());
		}catch(Exception e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL,
					MessageCommon.FAIL_MESSAGE);
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, headers = "Accept=application/json")
	@Transactional
	public @ResponseBody ResponseDTO register(String phonenumber, String password, String face_url, String nickname,
			String username) {
		try{
			UserDTO userDTO = userService.register(phonenumber, password, face_url, nickname, username);
			return ResponseUtil.ConvertToSuccessResponse(userDTO);
			
		}catch(ParameterException e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER);
		}catch(BusinessException e) {
			return ResponseUtil.ConvertToFailResponse(e.getCode(),
					e.getMessage());
		}catch(Exception e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL,
					MessageCommon.FAIL_MESSAGE);
		}
	}

	@RequestMapping(value = "/verify_code", method = RequestMethod.POST)
	public @ResponseBody ResponseDTO verify_code(String username, Long timestamp) {
		//TODO not completed. It should be verified By qiyueID, not username; 
		try{
			
			boolean result = userService.verify(username, timestamp);
			if (result) {
				return ResponseUtil.ConvertToSuccessResponse();
			} else {
				return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_USER_NOT_EXIST,
						MessageCommon.FAIL_MESSAGE_USER_NOT_EXIST);
			}
			
		}catch(ParameterException e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER);
		}catch(BusinessException e) {
			return ResponseUtil.ConvertToFailResponse(e.getCode(),
					e.getMessage());
		}catch(Exception e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL,
					MessageCommon.FAIL_MESSAGE);
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody Object update(String career, String phonenumber, String location, String birthday,
			String face_url, Integer uid, String token, String signature, String nickname, String school, Integer sex,
			BigDecimal latitude, BigDecimal longitude, String hometown, Long timestamp) {
		try{
			
			UserDTO userDTO = userService.updateUserInfo(career, phonenumber, location, birthday, face_url, 
					uid, token, signature, nickname, school, sex, latitude, longitude, hometown, timestamp);
			return ResponseUtil.ConvertToSuccessResponse(userDTO);
			
		}catch(ParameterException e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER);
		}catch(BusinessException e) {
			return ResponseUtil.ConvertToFailResponse(e.getCode(),
					e.getMessage());
		}catch(Exception e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL,
					MessageCommon.FAIL_MESSAGE);
		}
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