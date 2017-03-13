package qi.yue.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import qi.yue.common.DateJsonValueProcessor;
import qi.yue.common.MessageCommon;
import qi.yue.entity.Comment;
import qi.yue.entity.Moment;
import qi.yue.entity.ThumbsUp;
import qi.yue.entity.User;
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
		Map<String, Object> result = new HashMap<String, Object>();
		if (CommonUtil.isNullOrEmpty(msg) || CommonUtil.isNull(uid) || CommonUtil.isNullOrEmpty(video_url)
				|| CommonUtil.isNull(type) || CommonUtil.isNull(timestamp) || CommonUtil.isNullOrEmpty(pictures_url)
				|| CommonUtil.isNullOrEmpty(cover) || CommonUtil.isNullOrEmpty(token)) {
			result.put("data", "");
			result.put("status", MessageCommon.STATUS_FAIL);
		} else {
			String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp + MessageCommon.PUBLIC_KEY);
			if (!tokenTemp.equals(token)) {
				result.put("data", "");
				result.put("status", MessageCommon.STATUS_FAIL);
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
				result.put("data", moment);
				result.put("status", MessageCommon.STATUS_SUCCESS);
			}
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor());
		return JSONObject.fromObject(result, jsonConfig);
	}

	// @RequestMapping(value = "/show_moment", method = RequestMethod.POST)
	// public @ResponseBody Object showMoment(Integer user_id, Integer uid, Long
	// timestamp, String token, Integer pages) {
	// Map<String, Object> result = new HashMap<String, Object>();
	// if (CommonUtil.isNullOrEmpty(user_id) || CommonUtil.isNull(uid) ||
	// CommonUtil.isNullOrEmpty(timestamp)
	// || CommonUtil.isNull(pages) || CommonUtil.isNullOrEmpty(token)) {
	// result.put("data", "");
	// result.put("status", MessageCommon.STATUS_FAIL);
	// } else {
	// String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp +
	// MessageCommon.PUBLIC_KEY);
	// if (!tokenTemp.equals(token)) {
	// result.put("data", "");
	// result.put("status", MessageCommon.STATUS_FAIL);
	// } else {
	//
	// momentService.showMoment(user_id);
	// result.put("data", moment);
	// result.put("status", MessageCommon.STATUS_SUCCESS);
	// }
	// }
	// JsonConfig jsonConfig = new JsonConfig();
	// jsonConfig.registerJsonValueProcessor(Date.class, new
	// DateJsonValueProcessor());
	// return JSONObject.fromObject(result, jsonConfig);
	// }

	@RequestMapping(value = "/add_comment", method = RequestMethod.POST)
	public @ResponseBody Object addComment(String msg, Integer mid, Integer uid, String token, Long timestamp) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (CommonUtil.isNullOrEmpty(msg) || CommonUtil.isNullOrEmpty(mid) || CommonUtil.isNull(uid)
				|| CommonUtil.isNullOrEmpty(token) || CommonUtil.isNull(timestamp)) {
			result.put("data", "");
			result.put("status", MessageCommon.STATUS_FAIL);
		} else {
			String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp + MessageCommon.PUBLIC_KEY);
			if (!tokenTemp.equals(token)) {
				result.put("data", "");
				result.put("status", MessageCommon.STATUS_FAIL);
			} else {
				User user = userService.find(uid);
				Comment comment = new Comment();
				comment.setMomentId(mid);
				comment.setUserId(uid);
				comment.setMsg(msg);
				comment.setFaceUrl(user.getFaceUrl());
				comment.setNickname(user.getNickname());
				comment.setCreatedAt(new Date());
				commentService.save(comment);
				result.put("data", comment);
				result.put("status", MessageCommon.STATUS_SUCCESS);
			}
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor());
		return JSONObject.fromObject(result, jsonConfig);
	}

	@RequestMapping(value = "/remove_comment", method = RequestMethod.POST)
	public @ResponseBody Object removeComment(String token, Integer uid, Integer comment_id, Long timestamp) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (CommonUtil.isNullOrEmpty(token) || CommonUtil.isNull(uid) || CommonUtil.isNull(comment_id)
				|| CommonUtil.isNull(timestamp)) {
			result.put("data", "");
			result.put("status", MessageCommon.STATUS_FAIL);
		} else {
			String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp + MessageCommon.PUBLIC_KEY);
			if (!tokenTemp.equals(token)) {
				result.put("data", "");
				result.put("status", MessageCommon.STATUS_FAIL);
			} else {
				commentService.deleteByPrimaryKey(comment_id);
				result.put("status", MessageCommon.STATUS_SUCCESS);
			}
		}
		return result;
	}

	@RequestMapping(value = "/add_thumbsup", method = RequestMethod.POST)
	public @ResponseBody Object addThumbsup(String token, Integer uid, Integer mid, Long timestamp) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (CommonUtil.isNullOrEmpty(token) || CommonUtil.isNull(uid) || CommonUtil.isNull(mid)
				|| CommonUtil.isNull(timestamp)) {
			result.put("data", "");
			result.put("status", MessageCommon.STATUS_FAIL);
		} else {
			String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp + MessageCommon.PUBLIC_KEY);
			if (!tokenTemp.equals(token)) {
				result.put("data", "");
				result.put("status", MessageCommon.STATUS_FAIL);
			} else {
				User user = userService.find(uid);
				ThumbsUp thumbsUp = new ThumbsUp();
				thumbsUp.setMomentId(mid);
				thumbsUp.setUserId(uid);
				thumbsUp.setNickname(user.getNickname());
				thumbsUp.setCreatedAt(new Date());
				thumbsUpService.save(thumbsUp);
				result.put("status", MessageCommon.STATUS_SUCCESS);
			}
		}
		return result;
	}

	@RequestMapping(value = "/remove_thumbsup", method = RequestMethod.POST)
	public @ResponseBody Object removeThumbsup(String token, Integer uid, Integer thumbs_up_id, Long timestamp) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (CommonUtil.isNullOrEmpty(token) || CommonUtil.isNull(uid) || CommonUtil.isNull(thumbs_up_id)
				|| CommonUtil.isNull(timestamp)) {
			result.put("data", "");
			result.put("status", MessageCommon.STATUS_FAIL);
		} else {
			String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp + MessageCommon.PUBLIC_KEY);
			if (!tokenTemp.equals(token)) {
				result.put("data", "");
				result.put("status", MessageCommon.STATUS_FAIL);
			} else {
				thumbsUpService.deleteByThumbsUpId(thumbs_up_id);
				result.put("status", MessageCommon.STATUS_SUCCESS);
			}
		}
		return result;
	}

	@RequestMapping(value = "/remove_moment", method = RequestMethod.POST)
	public @ResponseBody Object removeMoment(String token, Integer uid, Integer mid, Long timestamp) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (CommonUtil.isNullOrEmpty(token) || CommonUtil.isNull(uid) || CommonUtil.isNull(mid)
				|| CommonUtil.isNull(timestamp)) {
			result.put("data", "");
			result.put("status", MessageCommon.STATUS_FAIL);
		} else {
			User user = userService.findByUidAndToken(uid, token);
			if (CommonUtil.isNull(user)) {
				result.put("data", "");
				result.put("status", MessageCommon.STATUS_FAIL);
			} else {
				thumbsUpService.deleteByThumbsUpId(mid);
				result.put("status", MessageCommon.STATUS_SUCCESS);
			}
		}
		return result;
	}
}