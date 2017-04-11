package qi.yue.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qi.yue.entity.Comment;
import qi.yue.entity.Moment;
import qi.yue.entity.ThumbsUp;
import qi.yue.exception.BusinessException;
import qi.yue.exception.ParameterException;
import qi.yue.common.MessageCommon;
import qi.yue.dao.mapper.CommentMapper;
import qi.yue.dao.mapper.FollowMapper;
import qi.yue.dao.mapper.MomentMapper;
import qi.yue.dao.mapper.ThumbsUpMapper;
import qi.yue.dao.mapper.UserMapper;
import qi.yue.dto.CommentDTO;
import qi.yue.dto.FollowDTO;
import qi.yue.dto.MomentDTO;
import qi.yue.dto.PageDTO;
import qi.yue.dto.ResponseDTO;
import qi.yue.dto.ThumbsUpDTO;
import qi.yue.dto.UserDTO;
import qi.yue.dto.assembler.CommentDTOAssembler;
import qi.yue.dto.assembler.ThumbsUpDTOAssembler;
import qi.yue.service.CommentService;
import qi.yue.service.MomentService;
import qi.yue.service.UserService;
import qi.yue.utils.CommonUtil;
import qi.yue.utils.ResponseUtil;
import qi.yue.utils.StringUtil;

@Service
public class MomentServiceImpl implements MomentService {
	@Resource
	private MomentMapper momentMapper;

	@Resource
	private CommentMapper commentMapper;

	@Resource
	private ThumbsUpMapper thumbsUpMapper;

	@Resource
	private FollowMapper followMapper;

	@Resource
	private UserMapper userMapper;

	@Resource
	private UserService userService;

	@Resource
	private CommentService commentService;

	/**
	 * 插入到悦圈内容表
	 * 
	 * @param moment
	 * @return int
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public int save(Moment moment) throws BusinessException {
		try {
			return momentMapper.insert(moment);
		} catch (BusinessException e) {
			throw new BusinessException(MessageCommon.STATUS_SAVE_FAIL, MessageCommon.FAIL_MESSAGE_SAVE_FAIL);
		}

	}

	@Override
	public MomentDTO find(int id) {
		return momentMapper.find(id);
	}

	@Override
	public List<MomentDTO> findByUserId(int userId) {
		return momentMapper.findByUserId(userId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int delete(int id) {
		return momentMapper.delete(id);
	}

	public List<MomentDTO> findByPage(PageDTO pageDTO) throws BusinessException {
		try {
			return momentMapper.findByPage(pageDTO);
		} catch (BusinessException e) {
			throw new BusinessException(MessageCommon.STATUS_QUERY_FAIL, MessageCommon.FAIL_MESSAGE_QUERY_FAIL);
		}

	}

	/**
	 * 查询所有关注人的悦圈内容
	 * 
	 * @param map
	 * @return List<MomentDTO>
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public List<MomentDTO> findFollowingsMoment(Map<String, Object> map) throws BusinessException {
		List<MomentDTO> MomentDTOList = momentMapper.findFollowingsMoment(map);
		for (MomentDTO momentDTO : MomentDTOList) {
			Map<String, Object> mapTemp1 = new HashMap<String, Object>();
			mapTemp1.put("fid", momentDTO.getUid());
			mapTemp1.put("tid", map.get("fid"));
			FollowDTO followDTO = followMapper.findByFidAndTid(mapTemp1);
			if (!CommonUtil.isNull(followDTO)) {
				momentDTO.getUser().setIs_followed(0);
			} else {
				momentDTO.getUser().setIs_followed(1);
			}
			Double distance = momentDTO.getDistance().divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP)
					.doubleValue();
			momentDTO.setDistance_string(distance.toString() + "km");
			List<CommentDTO> commentDTOList = commentMapper.findByMomentId(momentDTO.getMid());
			List<ThumbsUpDTO> thumbsUpDTOLsit = thumbsUpMapper.findByMomentId(momentDTO.getMid());
			momentDTO.setComment(commentDTOList);
			momentDTO.setThumbs_up(thumbsUpDTOLsit);
		}
		return MomentDTOList;
	}

	/**
	 * 查询附近人的悦圈内容
	 * 
	 * @param map
	 * @return List<MomentDTO>
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public List<MomentDTO> findNearbyMoment(Map<String, Object> map) throws BusinessException {
		try {
			List<MomentDTO> MomentDTOList = momentMapper.findNearbyMoment(map);
			for (MomentDTO momentDTO : MomentDTOList) {
				Map<String, Object> mapTemp1 = new HashMap<String, Object>();
				mapTemp1.put("fid", map.get("fid"));
				mapTemp1.put("tid", momentDTO.getUid());
				FollowDTO followDTO1 = followMapper.findByFidAndTid(mapTemp1);

				Map<String, Object> mapTemp2 = new HashMap<String, Object>();
				mapTemp2.put("fid", momentDTO.getUid());
				mapTemp2.put("tid", map.get("fid"));
				FollowDTO followDTO2 = followMapper.findByFidAndTid(mapTemp1);
				if (CommonUtil.isNull(followDTO1) && CommonUtil.isNull(followDTO2)) {
					momentDTO.getUser().setIs_followed(-1);
				} else if (!CommonUtil.isNull(followDTO1) && !CommonUtil.isNull(followDTO2)) {
					momentDTO.getUser().setIs_followed(0);
				} else {
					momentDTO.getUser().setIs_followed(1);
				}
				Double distance = momentDTO.getDistance().divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP)
						.doubleValue();
				momentDTO.setDistance_string(distance.toString() + "km");
				List<CommentDTO> commentDTOList = commentMapper.findByMomentId(momentDTO.getMid());
				List<ThumbsUpDTO> thumbsUpDTOLsit = thumbsUpMapper.findByMomentId(momentDTO.getMid());
				momentDTO.setComment(commentDTOList);
				momentDTO.setThumbs_up(thumbsUpDTOLsit);
			}
			return MomentDTOList;
		} catch (BusinessException e) {
			throw new BusinessException(MessageCommon.STATUS_QUERY_FAIL, MessageCommon.FAIL_MESSAGE_QUERY_FAIL);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void postMoment(String msg, Integer uid, String video_url, Integer type, Long timestamp,
			String[] pictures_url, String cover, String token, BigDecimal latitude, BigDecimal longitude,
			String moment_location) throws ParameterException, BusinessException {
		if (CommonUtil.isNullOrEmpty(msg) || CommonUtil.isNull(uid) || CommonUtil.isNull(type)
				|| CommonUtil.isNull(timestamp) || CommonUtil.isNullOrEmpty(token) || CommonUtil.isNull(latitude)
				|| CommonUtil.isNull(longitude) || CommonUtil.isNull(moment_location)) {
			throw new ParameterException();
		}
		ResponseDTO checkResult = checkType(type, pictures_url, cover, video_url, uid, timestamp, token);
		if (!checkResult.getStatus().equals(MessageCommon.STATUS_SUCCESS)) {
			throw new BusinessException(checkResult.getStatus(), checkResult.getMsg());
		}

		Moment moment = new Moment();
		moment.setMsg(msg);
		moment.setUserId(uid);
		moment.setVideo(video_url);
		moment.setType(type);
		if (!CommonUtil.isNullOrEmpty(pictures_url)) {
			moment.setPictures(StringUtil.arrayToString(pictures_url, ","));
		}
		moment.setCover(cover);
		moment.setLatitude(latitude);
		moment.setLongitude(longitude);
		moment.setMoment_location(moment_location);
		moment.setCreatedAt(new Date());
		moment.setUpdatedAt(new Date());
		save(moment);
	}

	private ResponseDTO checkType(Integer type, String[] pictures_url, String cover, String video_url, Integer uid,
			Long timestamp, String token) {
		if (!(type >= 0 && type <= 2)) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.TYPE_ERROR, MessageCommon.FAIL_MESSAGE_TYPE_ERROR);
		}
		if (1 == type && CommonUtil.isNullOrEmpty(pictures_url)) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.IMAGE_EMPTY,
					MessageCommon.FAIL_MESSAGE_IMAGE_EMPTY);
		}
		if (2 == type && (CommonUtil.isNullOrEmpty(cover) || CommonUtil.isNullOrEmpty(video_url))) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.VIDEO_EMPTY,
					MessageCommon.FAIL_MESSAGE_VIDEO_EMPTY);
		}
		// String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp +
		// MessageCommon.PUBLIC_KEY);
		// if (!tokenTemp.equals(token)) {
		// return ResponseUtil.ConvertToFailResponse();
		// }
		return ResponseUtil.ConvertToSuccessResponse();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<MomentDTO> showUserMoment(Integer user_id, Integer uid, Long timestamp, String token, Integer pages,
			BigDecimal latitude, BigDecimal longitude) throws ParameterException, BusinessException {
		if (CommonUtil.isNullOrEmpty(user_id) || CommonUtil.isNull(uid) || CommonUtil.isNullOrEmpty(timestamp)
				|| CommonUtil.isNull(pages) || CommonUtil.isNullOrEmpty(token)) {
			throw new ParameterException();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentNumber", pages * MessageCommon.PAGE_SIZE);
		map.put("size", MessageCommon.PAGE_SIZE);
		map.put("latitude", latitude);
		map.put("user_id", user_id);
		map.put("longitude", longitude);
		List<MomentDTO> MomentDTOList = momentMapper.findUserMoment(map);
		for (MomentDTO momentDTO : MomentDTOList) {
			Map<String, Object> mapTemp1 = new HashMap<String, Object>();
			mapTemp1.put("fid", uid);
			mapTemp1.put("tid", momentDTO.getUid());
			FollowDTO followDTO1 = followMapper.findByFidAndTid(mapTemp1);

			Map<String, Object> mapTemp2 = new HashMap<String, Object>();
			mapTemp2.put("fid", momentDTO.getUid());
			mapTemp2.put("tid", uid);
			FollowDTO followDTO2 = followMapper.findByFidAndTid(mapTemp1);
			if (CommonUtil.isNull(followDTO1) && CommonUtil.isNull(followDTO2)) {
				momentDTO.getUser().setIs_followed(-1);
			} else if (!CommonUtil.isNull(followDTO1) && !CommonUtil.isNull(followDTO2)) {
				momentDTO.getUser().setIs_followed(0);
			} else {
				momentDTO.getUser().setIs_followed(1);
			}
			if (!CommonUtil.isNullOrEmpty(momentDTO.getPictureString())) {
				momentDTO.setPictures(momentDTO.getPictureString().split(","));
			}
			Double distance = momentDTO.getDistance().divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP)
					.doubleValue();
			momentDTO.setDistance_string(distance.toString() + "km");
			List<CommentDTO> commentDTOList = commentMapper.findByMomentId(momentDTO.getMid());
			List<ThumbsUpDTO> thumbsUpDTOLsit = thumbsUpMapper.findByMomentId(momentDTO.getMid());
			momentDTO.setComment(commentDTOList);
			momentDTO.setThumbs_up(thumbsUpDTOLsit);
		}
		return MomentDTOList;
	}

	@Override
	public List<MomentDTO> showMoment(Integer uid, Integer type, Long timestamp, String token, Integer pages,
			BigDecimal latitude, BigDecimal longitude) throws ParameterException, BusinessException {
		if (CommonUtil.isNull(uid) || CommonUtil.isNull(type) || CommonUtil.isNullOrEmpty(timestamp)
				|| CommonUtil.isNull(pages) || CommonUtil.isNullOrEmpty(token) || CommonUtil.isNull(latitude)
				|| CommonUtil.isNull(longitude)) {
			throw new ParameterException();
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentNumber", pages * MessageCommon.PAGE_SIZE);
		map.put("size", MessageCommon.PAGE_SIZE);
		map.put("latitude", latitude);
		map.put("fid", uid);
		map.put("longitude", longitude);
		List<MomentDTO> momentDtos = null;
		if (0 == type) {
			momentDtos = findFollowingsMoment(map);
		} else if (1 == type) {
			momentDtos = findNearbyMoment(map);
		}
		for (MomentDTO momentDTO : momentDtos) {
			if (!CommonUtil.isNullOrEmpty(momentDTO.getPictureString())) {
				momentDTO.setPictures(momentDTO.getPictureString().split(","));
			}
		}
		return momentDtos;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public CommentDTO addComment(String msg, Integer mid, Integer uid, Integer reply_uid, String token,
			Long timestamp) {
		if (CommonUtil.isNullOrEmpty(msg) || CommonUtil.isNullOrEmpty(mid) || CommonUtil.isNull(uid)
				|| CommonUtil.isNullOrEmpty(token) || CommonUtil.isNull(timestamp)) {
			throw new ParameterException();
		}
		UserDTO user = userService.find(uid);
		Comment comment = new Comment();
		if (!CommonUtil.isNullOrEmpty(reply_uid)) {
			UserDTO user2 = userService.find(reply_uid);
			comment.setReplyUsername(user2.getUsername());
		}
		comment.setMomentId(mid);
		comment.setUserId(uid);
		comment.setReplyUid(reply_uid);

		comment.setMsg(msg);
		comment.setFaceUrl(user.getFace_url());
		comment.setUsername(user.getUsername());
		comment.setCreatedAt(new Date());
		commentService.save(comment);
		CommentDTO dto = CommentDTOAssembler.toDto(comment);
		return dto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void removeComment(String token, Integer uid, Integer comment_id, Long timestamp) {
		if (CommonUtil.isNullOrEmpty(token) || CommonUtil.isNullOrEmpty(uid) || CommonUtil.isNull(comment_id)
				|| CommonUtil.isNullOrEmpty(timestamp)) {
			throw new ParameterException();
		}
		commentMapper.delete(comment_id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ThumbsUpDTO addThumbsup(String token, Integer uid, Integer mid, Long timestamp) {
		if (CommonUtil.isNullOrEmpty(token) || CommonUtil.isNull(uid) || CommonUtil.isNull(mid)
				|| CommonUtil.isNull(timestamp)) {
			throw new ParameterException();
		}
		UserDTO user = userService.find(uid);
		ThumbsUp thumbsUp = new ThumbsUp();
		thumbsUp.setMomentId(mid);
		thumbsUp.setUserId(uid);
		thumbsUp.setUsername(user.getUsername());
		thumbsUp.setFaceUrl(user.getFace_url());
		thumbsUp.setSignature(user.getSignature());
		thumbsUp.setCreatedAt(new Date());
		thumbsUpMapper.insert(thumbsUp);
		ThumbsUpDTO dto = ThumbsUpDTOAssembler.toDto(thumbsUp);
		return dto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void removeThumbsup(String token, Integer uid, Integer thumbs_up_id, Long timestamp) {
		if (CommonUtil.isNullOrEmpty(token) || CommonUtil.isNullOrEmpty(uid) || CommonUtil.isNull(thumbs_up_id)
				|| CommonUtil.isNullOrEmpty(timestamp)) {
			throw new ParameterException();
		}
		thumbsUpMapper.delete(thumbs_up_id);
	}

	@Override
	public List<ThumbsUpDTO> showThumbsup(String token, Integer uid, Integer mid, Long timestamp, Integer pages) {
		if (CommonUtil.isNullOrEmpty(token) || CommonUtil.isNull(uid) || CommonUtil.isNull(mid)
				|| CommonUtil.isNull(pages) || CommonUtil.isNull(timestamp)) {
			throw new ParameterException();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mid", mid);
		map.put("size", MessageCommon.PAGE_SIZE);
		map.put("currentNumber", pages * MessageCommon.PAGE_SIZE);
		return thumbsUpMapper.findThumbsUpByMid(map);
	}
}
