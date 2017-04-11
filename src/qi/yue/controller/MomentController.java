package qi.yue.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import qi.yue.common.MessageCommon;
import qi.yue.dto.CommentDTO;
import qi.yue.dto.MomentDTO;
import qi.yue.dto.ResponseDTO;
import qi.yue.dto.ThumbsUpDTO;
import qi.yue.dto.UserDTO;
import qi.yue.exception.BusinessException;
import qi.yue.exception.ParameterException;
import qi.yue.service.CommentService;
import qi.yue.service.MomentService;
import qi.yue.service.ThumbsUpService;
import qi.yue.service.UserService;
import qi.yue.utils.CommonUtil;
import qi.yue.utils.ResponseUtil;

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
	public @ResponseBody Object showUserMoment(Integer user_id, Integer uid, Long timestamp, String token,
			Integer pages, BigDecimal latitude, BigDecimal longitude) {
		try {
			List<MomentDTO> data = momentService.showUserMoment(user_id, uid, timestamp, token, pages, latitude,
					longitude);
			ResponseDTO responseDTO = ResponseUtil.ConvertToSuccessResponse(data);
			return responseDTO;
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

	@RequestMapping(value = "/show_moment", method = RequestMethod.POST)
	public @ResponseBody Object showMoment(Integer uid, Integer type, Long timestamp, String token, Integer pages,
			BigDecimal latitude, BigDecimal longitude) {
		try {
			List<MomentDTO> data = momentService.showMoment(uid, type, timestamp, token, pages, latitude, longitude);
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

	@RequestMapping(value = "/add_comment", method = RequestMethod.POST)
	public @ResponseBody Object addComment(String msg, Integer mid, Integer uid, Integer reply_uid, String token,
			Long timestamp) {
		try {
			CommentDTO data = momentService.addComment(msg, mid, uid, reply_uid, token, timestamp);
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

	@RequestMapping(value = "/remove_comment", method = RequestMethod.POST)
	public @ResponseBody Object removeComment(String token, Integer uid, Integer comment_id, Long timestamp) {
		try {
			momentService.removeComment(token, uid, comment_id, timestamp);
			return ResponseUtil.ConvertToSuccessResponse(null);
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

	@RequestMapping(value = "/add_thumbsup", method = RequestMethod.POST)
	public @ResponseBody Object addThumbsup(String token, Integer uid, Integer mid, Long timestamp) {
		try {
			ThumbsUpDTO data = momentService.addThumbsup(token, uid, mid, timestamp);
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

	@RequestMapping(value = "/remove_thumbsup", method = RequestMethod.POST)
	public @ResponseBody Object removeThumbsup(String token, Integer uid, Integer thumbs_up_id, Long timestamp) {
		try {
			momentService.removeThumbsup(token, uid, thumbs_up_id, timestamp);
			return ResponseUtil.ConvertToSuccessResponse(null);
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

	@RequestMapping(value = "/remove_moment", method = RequestMethod.POST)
	public @ResponseBody Object removeMoment(String token, Integer uid, Integer comment_id, Long timestamp) {
		ResponseDTO responseDTO = new ResponseDTO();
		if (CommonUtil.isNullOrEmpty(token) || CommonUtil.isNull(uid) || CommonUtil.isNull(uid)
				|| CommonUtil.isNull(timestamp)) {
			responseDTO.setStatus(MessageCommon.STATUS_FAIL);
			return responseDTO;
		}
		// String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp +
		// MessageCommon.PUBLIC_KEY);
		// if (!tokenTemp.equals(token)) {
		// responseDTO.setStatus(MessageCommon.STATUS_FAIL);
		// } else {
		momentService.delete(uid);
		responseDTO.setStatus(MessageCommon.STATUS_SUCCESS);
		// }
		return responseDTO;
	}

	@RequestMapping(value = "/show_thumbsup", method = RequestMethod.POST)
	public @ResponseBody Object showThumbsup(String token, Integer uid, Integer mid, Long timestamp, Integer pages) {
		try {
			List<ThumbsUpDTO> data = momentService.showThumbsup(token, uid, mid, timestamp, pages);
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