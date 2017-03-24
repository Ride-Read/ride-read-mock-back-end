package qi.yue.controller;

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
import qi.yue.service.CommentService;
import qi.yue.service.MomentService;
import qi.yue.service.ThumbsUpService;
import qi.yue.service.UserService;
import qi.yue.utils.CommonUtil;
import qi.yue.utils.EncryptionUtil;
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
	public @ResponseBody Object postMoment(String msg, Integer uid, String video_url, Integer type, Long timestamp,
			String[] pictures_url, String cover, String token) {
		ResponseDTO responseDTO = new ResponseDTO();
		if (CommonUtil.isNullOrEmpty(msg) || CommonUtil.isNull(uid) || CommonUtil.isNull(type)
				|| CommonUtil.isNull(timestamp) || CommonUtil.isNullOrEmpty(token)) {
			responseDTO.setStatus(MessageCommon.STATUS_FAIL);
			return responseDTO;
		}
		if (!(type >= 0 && type <= 2)) {
			responseDTO.setStatus(MessageCommon.TYPE_ERROR);
			return responseDTO;
		}
		if (1 == type && CommonUtil.isNullOrEmpty(pictures_url)) {
			responseDTO.setStatus(MessageCommon.IMAGE_EMPTY);
			return responseDTO;
		}
		if (2 == type && (CommonUtil.isNullOrEmpty(cover) || CommonUtil.isNullOrEmpty(video_url))) {
			responseDTO.setStatus(MessageCommon.VIDEO_EMPTY);
			return responseDTO;
		}
		String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp + MessageCommon.PUBLIC_KEY);
		if (!tokenTemp.equals(token)) {
			responseDTO.setStatus(MessageCommon.STATUS_FAIL);
		} else {
			Moment moment = new Moment();
			moment.setMsg(msg);
			moment.setUserId(uid);
			moment.setVideo(video_url);
			moment.setType(type);
			moment.setPictures(StringUtil.arrayToString(pictures_url, ","));
			moment.setCover(cover);
			moment.setCreatedAt(new Date());
			moment.setUpdatedAt(new Date());
			momentService.save(moment);
			MomentDTO dto = MomentDtoAssembler.toDto(moment);
			responseDTO.setData(dto);
			responseDTO.setStatus(MessageCommon.STATUS_SUCCESS);
		}
		return responseDTO;
	}

	@RequestMapping(value = "/show_user", method = RequestMethod.POST)
	public @ResponseBody Object showUserMoment(Integer user_id, Integer uid, Long timestamp, String token,
			Integer pages) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (CommonUtil.isNullOrEmpty(user_id) || CommonUtil.isNull(uid) || CommonUtil.isNullOrEmpty(timestamp)
				|| CommonUtil.isNull(pages) || CommonUtil.isNullOrEmpty(token)) {
			result.put("data", "");
			result.put("status", MessageCommon.STATUS_FAIL);
		} else {
			String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp + MessageCommon.PUBLIC_KEY);
			if (!tokenTemp.equals(token)) {
				result.put("data", "");
				result.put("status", MessageCommon.STATUS_FAIL);
			} else {
				PageDTO pageDTO = new PageDTO();
				pageDTO.setId(user_id);
				pageDTO.setCurrentNumberFromPages(pages);
				List<MomentDTO> momentDtos = momentService.findByPage(pageDTO);
				for (MomentDTO momentDTO : momentDtos) {
					if (CommonUtil.isNullOrEmpty(momentDTO.getPictureString())) {
						momentDTO.setPictures(momentDTO.getPictureString().split(","));
					}
				}
				UserDTO userDto = userService.find(user_id);
				result.put("data", momentDtos);
				result.put("user", userDto);
				result.put("status", MessageCommon.STATUS_SUCCESS);
			}
		}
		return result;
	}

	@RequestMapping(value = "/show_moment", method = RequestMethod.POST)
	public @ResponseBody Object showMoment(Integer uid, Integer type, Long timestamp, String token, Integer pages) {
		ResponseDTO responseDTO = new ResponseDTO();
		if (CommonUtil.isNull(uid) || CommonUtil.isNull(type) || CommonUtil.isNullOrEmpty(timestamp)
				|| CommonUtil.isNull(pages) || CommonUtil.isNullOrEmpty(token)) {
			responseDTO.setStatus(MessageCommon.STATUS_FAIL);
			return responseDTO;
		}
		// String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp +
		// MessageCommon.PUBLIC_KEY);
		// if (!tokenTemp.equals(token)) {
		// responseDTO.setStatus(MessageCommon.STATUS_FAIL);
		// } else {
		PageDTO pageDTO = new PageDTO();
		pageDTO.setId(uid);
		pageDTO.setCurrentNumberFromPages(pages);
		List<MomentDTO> momentDtos = momentService.findFollowingsMoment(pageDTO);
		if (0 == type) {
			momentDtos = momentService.findFollowingsMoment(pageDTO);
		} else {
			momentDtos = momentService.findFollowingsMoment(pageDTO);
		}
		for (MomentDTO momentDTO : momentDtos) {
			if (CommonUtil.isNullOrEmpty(momentDTO.getPictureString())) {
				momentDTO.setPictures(momentDTO.getPictureString().split(","));
			}
		}
		// UserDTO userDto = userService.find(user_id);
		responseDTO.setData(momentDtos);
		// result.put("user", userDto);
		responseDTO.setStatus(MessageCommon.STATUS_SUCCESS);
		// }
		return responseDTO;
	}

	@RequestMapping(value = "/add_comment", method = RequestMethod.POST)
	public @ResponseBody Object addComment(String msg, Integer mid, Integer uid, Integer reply_uid, String token,
			Long timestamp) {
		ResponseDTO responseDTO = new ResponseDTO();
		if (CommonUtil.isNullOrEmpty(msg) || CommonUtil.isNullOrEmpty(mid) || CommonUtil.isNull(uid)
				|| CommonUtil.isNullOrEmpty(token) || CommonUtil.isNull(timestamp)) {
			responseDTO.setStatus(MessageCommon.STATUS_FAIL);
			return responseDTO;
		}
		String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp + MessageCommon.PUBLIC_KEY);
		if (!tokenTemp.equals(token)) {
			responseDTO.setStatus(MessageCommon.STATUS_FAIL);
		} else {
			UserDTO user = userService.find(uid);
			Comment comment = new Comment();
			comment.setMomentId(mid);
			comment.setUserId(uid);
			comment.setReplyUid(reply_uid);
			comment.setMsg(msg);
			comment.setFaceUrl(user.getFace_url());
			comment.setNickname(user.getNickname());
			comment.setCreatedAt(new Date());
			commentService.save(comment);
			CommentDTO dto = CommentDtoAssembler.toDto(comment);
			responseDTO.setData(dto);
			responseDTO.setStatus(MessageCommon.STATUS_SUCCESS);
		}
		return responseDTO;
	}

	@RequestMapping(value = "/remove_comment", method = RequestMethod.POST)
	public @ResponseBody Object removeComment(String token, Integer uid, Integer comment_id, Long timestamp) {
		ResponseDTO responseDTO = new ResponseDTO();
		if (CommonUtil.isNullOrEmpty(token) || CommonUtil.isNull(uid) || CommonUtil.isNull(comment_id)
				|| CommonUtil.isNull(timestamp)) {
			responseDTO.setStatus(MessageCommon.STATUS_FAIL);
			return responseDTO;
		}
		String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp + MessageCommon.PUBLIC_KEY);
		if (!tokenTemp.equals(token)) {
			responseDTO.setStatus(MessageCommon.STATUS_FAIL);
		} else {
			commentService.delete(comment_id);
			responseDTO.setStatus(MessageCommon.STATUS_SUCCESS);
		}
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
		String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp + MessageCommon.PUBLIC_KEY);
		if (!tokenTemp.equals(token)) {
			responseDTO.setStatus(MessageCommon.STATUS_FAIL);
		} else {
			UserDTO user = userService.find(uid);
			ThumbsUp thumbsUp = new ThumbsUp();
			thumbsUp.setMomentId(mid);
			thumbsUp.setUserId(uid);
			thumbsUp.setNickname(user.getNickname());
			thumbsUp.setCreatedAt(new Date());
			thumbsUpService.save(thumbsUp);
			ThumbsUpDTO dto = ThumbsUpDtoAssembler.toDto(thumbsUp);
			responseDTO.setData(dto);
			responseDTO.setStatus(MessageCommon.STATUS_SUCCESS);
		}
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
		String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp + MessageCommon.PUBLIC_KEY);
		if (!tokenTemp.equals(token)) {
			responseDTO.setStatus(MessageCommon.STATUS_FAIL);
		} else {
			thumbsUpService.delete(thumbs_up_id);
			responseDTO.setStatus(MessageCommon.STATUS_SUCCESS);
		}
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
		String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp + MessageCommon.PUBLIC_KEY);
		if (!tokenTemp.equals(token)) {
			responseDTO.setStatus(MessageCommon.STATUS_FAIL);
		} else {
			momentService.delete(mid);
			responseDTO.setStatus(MessageCommon.STATUS_SUCCESS);
		}
		return responseDTO;
	}
}