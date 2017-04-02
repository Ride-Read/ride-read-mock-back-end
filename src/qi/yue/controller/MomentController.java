package qi.yue.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import qi.yue.common.MessageCommon;
import qi.yue.dto.CommentDTO;
import qi.yue.dto.MomentDTO;
import qi.yue.dto.PageDTO;
import qi.yue.dto.ResponseDTO;
import qi.yue.dto.ThumbsUpDTO;
import qi.yue.dto.UserDTO;
import qi.yue.dto.assembler.CommentDtoAssembler;
import qi.yue.dto.assembler.MomentDtoAssembler;
import qi.yue.dto.assembler.ThumbsUpDtoAssembler;
import qi.yue.entity.Comment;
import qi.yue.entity.Moment;
import qi.yue.entity.ThumbsUp;
import qi.yue.exception.BusinessException;
import qi.yue.exception.ParameterException;
import qi.yue.service.CommentService;
import qi.yue.service.MomentService;
import qi.yue.service.ThumbsUpService;
import qi.yue.service.UserService;
import qi.yue.utils.CommonUtil;
import qi.yue.utils.EncryptionUtil;
import qi.yue.utils.ResponseUtil;
import qi.yue.utils.StringUtil;

@Controller
@RequestMapping("/moments")
public class MomentController {
	@Resource
	private UserService userService;

	@Resource
	private ThumbsUpService thumbsUpService;

	@Resource
	private CommentService commentService;

	@Resource
	private MomentService momentService;

	@RequestMapping(value = "/post_moment", method = RequestMethod.POST)
	public @ResponseBody ResponseDTO postMoment(String msg, Integer uid, String video_url, Integer type, Long timestamp,
			String[] pictures_url, String cover, String token, BigDecimal latitude, BigDecimal longitude) {
		try {

			momentService.postMoment(msg, uid, video_url, type, timestamp, pictures_url, cover, token, latitude,
					longitude);
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

	@RequestMapping(value = "/show_user", method = RequestMethod.POST)
	public @ResponseBody ResponseDTO showUserMoment(Integer user_id, Integer uid, Long timestamp, String token,
			Integer pages) {
		try {

			Map<String, Object> data = momentService.showUserMoment(user_id, uid, timestamp, token, pages);
			return ResponseUtil.ConvertToSuccessResponse(data);

		} catch (ParameterException e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER);
		} catch (BusinessException e) {
			return ResponseUtil.ConvertToFailResponse(e.getCode(), e.getMessage());
		} catch (Exception e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL, MessageCommon.FAIL_MESSAGE);
		}
	}

	@RequestMapping(value = "/show_moment", method = RequestMethod.POST)
	public @ResponseBody Object showMoment(Integer uid, Integer type, Long timestamp, String token, Integer pages,
			BigDecimal latitude, BigDecimal longitude) {
		try {

			List<MomentDTO> data = momentService.showMoment(uid, type, timestamp, token, pages, latitude, longitude);
			return ResponseUtil.ConvertToSuccessResponse(data);

		} catch (ParameterException e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER);
		} catch (BusinessException e) {
			return ResponseUtil.ConvertToFailResponse(e.getCode(), e.getMessage());
		} catch (Exception e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL, MessageCommon.FAIL_MESSAGE);
		}

	}

	@RequestMapping(value = "/add_comment", method = RequestMethod.POST)
	public @ResponseBody Object addComment(String msg, Integer mid, Integer uid, Integer reply_uid, String token,
			Long timestamp) {
		try {

			CommentDTO data = momentService.addComment(msg, mid, uid, reply_uid, token, timestamp);
			return ResponseUtil.ConvertToSuccessResponse(data);

		} catch (ParameterException e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER);
		} catch (BusinessException e) {
			return ResponseUtil.ConvertToFailResponse(e.getCode(), e.getMessage());
		} catch (Exception e) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL, MessageCommon.FAIL_MESSAGE);
		}
	}

	@RequestMapping(value = "/remove_comment", method = RequestMethod.POST)
	public @ResponseBody Object removeComment(String token, Integer uid, Integer comment_id, Long timestamp) {
		ResponseDTO responseDTO = new ResponseDTO();
		if (CommonUtil.isNullOrEmpty(token) || CommonUtil.isNull(uid) || CommonUtil.isNull(comment_id)
				|| CommonUtil.isNull(timestamp)) {
			responseDTO.setStatus(MessageCommon.STATUS_FAIL);
			return responseDTO;
		}
		// String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp +
		// MessageCommon.PUBLIC_KEY);
		// if (!tokenTemp.equals(token)) {
		// responseDTO.setStatus(MessageCommon.STATUS_FAIL);
		// } else {
		commentService.delete(comment_id);
		responseDTO.setStatus(MessageCommon.STATUS_SUCCESS);
		// }
		return responseDTO;
	}

	@RequestMapping(value = "/add_thumbsup", method = RequestMethod.POST)
	public @ResponseBody Object addThumbsup(String token, Integer uid, Integer mid, Long timestamp) {
		ResponseDTO responseDTO = new ResponseDTO();
		if (CommonUtil.isNullOrEmpty(token) || CommonUtil.isNull(uid) || CommonUtil.isNull(mid)
				|| CommonUtil.isNull(timestamp)) {
			responseDTO.setStatus(MessageCommon.STATUS_FAIL);
			return responseDTO;
		}
		// String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp +
		// MessageCommon.PUBLIC_KEY);
		// if (!tokenTemp.equals(token)) {
		// responseDTO.setStatus(MessageCommon.STATUS_FAIL);
		// } else {
		UserDTO user = userService.find(uid);
		ThumbsUp thumbsUp = new ThumbsUp();
		thumbsUp.setMomentId(mid);
		thumbsUp.setUserId(uid);
		// thumbsUp.setNickname(user.getNickname());
		thumbsUp.setCreatedAt(new Date());
		thumbsUpService.save(thumbsUp);
		ThumbsUpDTO dto = ThumbsUpDtoAssembler.toDto(thumbsUp);
		responseDTO.setData(dto);
		responseDTO.setStatus(MessageCommon.STATUS_SUCCESS);
		// }
		return responseDTO;
	}

	@RequestMapping(value = "/remove_thumbsup", method = RequestMethod.POST)
	public @ResponseBody Object removeThumbsup(String token, Integer uid, Integer thumbs_up_id, Long timestamp) {
		ResponseDTO responseDTO = new ResponseDTO();
		if (CommonUtil.isNullOrEmpty(token) || CommonUtil.isNull(uid) || CommonUtil.isNull(thumbs_up_id)
				|| CommonUtil.isNull(timestamp)) {
			responseDTO.setStatus(MessageCommon.STATUS_FAIL);
			return responseDTO;
		}
		// String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp +
		// MessageCommon.PUBLIC_KEY);
		// if (!tokenTemp.equals(token)) {
		// responseDTO.setStatus(MessageCommon.STATUS_FAIL);
		// } else {
		thumbsUpService.delete(thumbs_up_id);
		responseDTO.setStatus(MessageCommon.STATUS_SUCCESS);
		// }
		return responseDTO;
	}

	@RequestMapping(value = "/remove_moment", method = RequestMethod.POST)
	public @ResponseBody Object removeMoment(String token, Integer uid, Integer mid, Long timestamp) {
		ResponseDTO responseDTO = new ResponseDTO();
		if (CommonUtil.isNullOrEmpty(token) || CommonUtil.isNull(uid) || CommonUtil.isNull(mid)
				|| CommonUtil.isNull(timestamp)) {
			responseDTO.setStatus(MessageCommon.STATUS_FAIL);
			return responseDTO;
		}
		// String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp +
		// MessageCommon.PUBLIC_KEY);
		// if (!tokenTemp.equals(token)) {
		// responseDTO.setStatus(MessageCommon.STATUS_FAIL);
		// } else {
		momentService.delete(mid);
		responseDTO.setStatus(MessageCommon.STATUS_SUCCESS);
		// }
		return responseDTO;
	}

	@RequestMapping(value = "/show_thumbsup", method = RequestMethod.POST)
	public @ResponseBody Object showThumbsup(String token, Integer uid, Integer mid, Long timestamp, Integer pages) {
		ResponseDTO responseDTO = new ResponseDTO();
		if (CommonUtil.isNullOrEmpty(token) || CommonUtil.isNull(uid) || CommonUtil.isNull(mid)
				|| CommonUtil.isNull(pages) || CommonUtil.isNull(timestamp)) {
			responseDTO.setStatus(MessageCommon.STATUS_FAIL);
			return responseDTO;
		}
		// String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp +
		// MessageCommon.PUBLIC_KEY);
		// if (!tokenTemp.equals(token)) {
		// responseDTO.setStatus(MessageCommon.STATUS_FAIL);
		// } else {
		UserDTO user = userService.findThumbsUpUserByMid(mid);
		responseDTO.setData(user);
		responseDTO.setStatus(MessageCommon.STATUS_SUCCESS);
		// }
		return responseDTO;
	}
}