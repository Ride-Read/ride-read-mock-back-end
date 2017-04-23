package qi.yue.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import qi.yue.dto.MomentDTO;
import qi.yue.dto.PageDTO;
import qi.yue.dto.ResponseDTO;
import qi.yue.dto.ThumbsUpDTO;
import qi.yue.dto.UserDTO;
import qi.yue.dto.assembler.CommentDTOAssembler;
import qi.yue.dto.assembler.MomentDTOAssembler;
import qi.yue.dto.assembler.ThumbsUpDTOAssembler;
import qi.yue.service.CommentService;
import qi.yue.service.FollowService;
import qi.yue.service.MomentService;
import qi.yue.service.UserService;
import qi.yue.utils.CommonUtil;
import qi.yue.utils.DistanceUtil;
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

	@Resource
	private FollowService followService;

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
			// Map<String, Object> mapTemp1 = new HashMap<String, Object>();
			// mapTemp1.put("fid", momentDTO.getUid());
			// mapTemp1.put("tid", map.get("fid"));
			// FollowDTO followDTO = followMapper.findByFidAndTid(mapTemp1);
			// if (!CommonUtil.isNull(followDTO)) {
			// momentDTO.getUser().setIs_followed(0);
			// } else {
			// momentDTO.getUser().setIs_followed(1);
			// }
			Integer isFollow = followService.isFollow(Integer.parseInt(map.get("fid").toString()), momentDTO.getUid());
			momentDTO.getUser().setIs_followed(isFollow);
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
				Integer isFollow = followService.isFollow(Integer.parseInt(map.get("fid").toString()),
						momentDTO.getUid());
				// Map<String, Object> mapTemp1 = new HashMap<String, Object>();
				// mapTemp1.put("fid", map.get("fid"));
				// mapTemp1.put("tid", momentDTO.getUid());
				// FollowDTO followDTO1 =
				// followMapper.findByFidAndTid(mapTemp1);
				//
				// Map<String, Object> mapTemp2 = new HashMap<String, Object>();
				// mapTemp2.put("fid", momentDTO.getUid());
				// mapTemp2.put("tid", map.get("fid"));
				// FollowDTO followDTO2 =
				// followMapper.findByFidAndTid(mapTemp1);
				// if (CommonUtil.isNull(followDTO1) &&
				// CommonUtil.isNull(followDTO2)) {
				// momentDTO.getUser().setIs_followed(-1);
				// } else if (!CommonUtil.isNull(followDTO1) &&
				// !CommonUtil.isNull(followDTO2)) {
				// momentDTO.getUser().setIs_followed(0);
				// } else {
				// momentDTO.getUser().setIs_followed(1);
				// }
				momentDTO.getUser().setIs_followed(isFollow);
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
	public MomentDTO postMoment(String msg, Integer uid, String video_url, Integer type, Long timestamp,
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
		return MomentDTOAssembler.toDto(moment);
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

			// Map<String, Object> mapTemp1 = new HashMap<String, Object>();
			// mapTemp1.put("fid", uid);
			// mapTemp1.put("tid", momentDTO.getUid());
			// FollowDTO followDTO1 = followMapper.findByFidAndTid(mapTemp1);
			//
			// Map<String, Object> mapTemp2 = new HashMap<String, Object>();
			// mapTemp2.put("fid", momentDTO.getUid());
			// mapTemp2.put("tid", uid);
			// FollowDTO followDTO2 = followMapper.findByFidAndTid(mapTemp1);
			// if (CommonUtil.isNull(followDTO1) &&
			// CommonUtil.isNull(followDTO2)) {
			// momentDTO.getUser().setIs_followed(-1);
			// } else if (!CommonUtil.isNull(followDTO1) &&
			// !CommonUtil.isNull(followDTO2)) {
			// momentDTO.getUser().setIs_followed(0);
			// } else {
			// momentDTO.getUser().setIs_followed(1);
			// }
			Integer isFollow = followService.isFollow(uid, user_id);
			momentDTO.getUser().setIs_followed(isFollow);
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
		Map<String, Object> map = new HashMap<>();
		map.put("mid", mid);
		map.put("uid", uid);
		ThumbsUpDTO thumbsUpDTO = thumbsUpMapper.findByMidAndUid(map);
		if (!CommonUtil.isNullOrEmpty(thumbsUpDTO)) {
			throw new BusinessException(MessageCommon.STATUS_REPEAT_THUMBS_UP,
					MessageCommon.FAIL_MESSAGE_REPEAT_THUMBS_UP);
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
		List<ThumbsUpDTO> thumbsUpDTOList = thumbsUpMapper.findThumbsUpByMid(map);
		for (ThumbsUpDTO thumbsUpDTO : thumbsUpDTOList) {
			// Map<String, Object> mapTemp1 = new HashMap<String, Object>();
			// mapTemp1.put("fid", uid);
			// mapTemp1.put("tid", thumbsUpDTO.getUid());
			// FollowDTO followDTO1 = followMapper.findByFidAndTid(mapTemp1);
			//
			// Map<String, Object> mapTemp2 = new HashMap<String, Object>();
			// mapTemp2.put("fid", thumbsUpDTO.getUid());
			// mapTemp2.put("tid", uid);
			// FollowDTO followDTO2 = followMapper.findByFidAndTid(mapTemp1);
			// if (CommonUtil.isNull(followDTO1) &&
			// CommonUtil.isNull(followDTO2)) {
			// thumbsUpDTO.setIs_followed(-1);
			// } else if (!CommonUtil.isNull(followDTO1) &&
			// !CommonUtil.isNull(followDTO2)) {
			// thumbsUpDTO.setIs_followed(0);
			// } else {
			// thumbsUpDTO.setIs_followed(1);
			// }
			Integer isFollow = followService.isFollow(uid, thumbsUpDTO.getUid());
			thumbsUpDTO.setIs_followed(isFollow);
		}
		return thumbsUpDTOList;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ThumbsUpDTO updateThumbsup(String token, Integer uid, Integer mid, Long timestamp) {
		if (CommonUtil.isNullOrEmpty(token) || CommonUtil.isNull(uid) || CommonUtil.isNull(mid)
				|| CommonUtil.isNull(timestamp)) {
			throw new ParameterException();
		}
		UserDTO user = userService.find(uid);
		Map<String, Object> map = new HashMap<>();
		map.put("mid", mid);
		map.put("uid", uid);
		ThumbsUpDTO thumbsUpDTO = thumbsUpMapper.findByMidAndUid(map);
		if (CommonUtil.isNull(thumbsUpDTO)) {
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
		} else {
			thumbsUpMapper.delete(thumbsUpDTO.getThumbs_up_id());
			return null;
		}
	}

	@Override
	public List<MomentDTO> findUserMap(Integer uid, Long timestamp, String token) {
		if (CommonUtil.isNullOrEmpty(uid) || CommonUtil.isNull(timestamp) || CommonUtil.isNull(token)) {
			throw new ParameterException();
		}
		List<MomentDTO> momentDTOList = momentMapper.findUserMap(uid);
		for (MomentDTO momentDTO : momentDTOList) {
			if (!CommonUtil.isNullOrEmpty(momentDTO.getPictureString())) {
				String[] pictures = momentDTO.getPictureString().split(",");
				momentDTO.setFirst_picture(pictures[0]);
			}
		}
		return momentDTOList;
	}

	@Override
	public List<MomentDTO> findNearMap(Integer uid, Long timestamp, String token, BigDecimal latitude,
			BigDecimal longitude) {
		if (CommonUtil.isNullOrEmpty(uid) || CommonUtil.isNull(timestamp) || CommonUtil.isNull(token)
				|| CommonUtil.isNull(latitude) || CommonUtil.isNull(longitude)) {
			throw new ParameterException();
		}
		Double lat = latitude.doubleValue();
		Double lon = longitude.doubleValue();
		Double[] maxLatAndLog = DistanceUtil.getAround(lat, lon, MessageCommon.DISTANCE_AROUND);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("minLat", maxLatAndLog[0]);
		map.put("minLng", maxLatAndLog[1]);
		map.put("maxLat", maxLatAndLog[2]);
		map.put("maxLng", maxLatAndLog[3]);
		map.put("latitude", latitude);
		map.put("longitude", longitude);
		List<MomentDTO> moments = momentMapper.findNearMap(map);
		for (MomentDTO momentDTO : moments) {
			if (!CommonUtil.isNullOrEmpty(momentDTO.getPictureString())) {
				String[] pictures = momentDTO.getPictureString().split(",");
				momentDTO.setFirst_picture(pictures[0]);
			}
		}
		return moments;
	}

	@Transactional(rollbackFor = Exception.class)
	public MomentDTO showOneMoment(Integer uid, Integer mid, Long timestamp, String token, BigDecimal latitude,
			BigDecimal longitude) {
		if (CommonUtil.isNullOrEmpty(uid) || CommonUtil.isNullOrEmpty(mid) || CommonUtil.isNull(timestamp)
				|| CommonUtil.isNull(token) || CommonUtil.isNull(latitude) || CommonUtil.isNull(longitude)) {
			throw new ParameterException();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", mid);
		map.put("latitude", latitude);
		map.put("longitude", longitude);
		MomentDTO momentDTO = momentMapper.findMoment(map);
		Integer isFollow = followService.isFollow(uid, momentDTO.getUid());
		momentDTO.getUser().setIs_followed(isFollow);
		Double distance = momentDTO.getDistance().divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
		momentDTO.setDistance_string(distance.toString() + "km");
		List<CommentDTO> commentDTOList = commentMapper.findByMomentId(momentDTO.getMid());
		List<ThumbsUpDTO> thumbsUpDTOLsit = thumbsUpMapper.findByMomentId(momentDTO.getMid());
		momentDTO.setComment(commentDTOList);
		momentDTO.setThumbs_up(thumbsUpDTOLsit);
		return momentDTO;
	}

	@Override
	public List<MomentDTO> showMapNumber(Integer uid, Long timestamp, String token, BigDecimal latitude,
			BigDecimal longitude, Double scaling_ratio) {
		Double lat = latitude.doubleValue();
		Double lon = longitude.doubleValue();
		Double[] maxLatAndLog = DistanceUtil.getAround(lat, lon, MessageCommon.DISTANCE_AROUND);
		Double intervalRadiusLat = (lat - maxLatAndLog[0]) / 3;
		Double intervalRadiusLon = (lon - maxLatAndLog[1]) / 3;
		Double intervalRadiusLat2 = intervalRadiusLat * 2;
		Double intervalRadiusLon2 = intervalRadiusLon * 2;
		List<MomentDTO> moments = new ArrayList<>();

		Map<String, Object> map1 = new HashMap<String, Object>();// 中心点范围
		map1.put("minLat", maxLatAndLog[0] + intervalRadiusLat2);// 最小纬度
		map1.put("minLng", maxLatAndLog[1] + intervalRadiusLon2);// 最小经度
		map1.put("maxLat", maxLatAndLog[2] - intervalRadiusLat2);// 最大纬度
		map1.put("maxLng", maxLatAndLog[3] - intervalRadiusLon2);// 最大经度
		map1.put("latitude", lat);
		map1.put("longitude", lon);
		List<MomentDTO> moments1 = momentMapper.findWithtThumbsUpCount(map1);
		MomentDTO momentDTO1 = null;
		if (!CommonUtil.isNullOrEmpty(moments1)) {
			momentDTO1 = moments1.get(0);
			momentDTO1.setCount(moments1.size());
			moments.add(momentDTO1);
		}

		Map<String, Object> map2 = new HashMap<String, Object>();// 中心点下面的一点
		map2.put("minLat", maxLatAndLog[0]);
		map2.put("minLng", maxLatAndLog[1] + intervalRadiusLon2);
		map2.put("maxLat", maxLatAndLog[2] - intervalRadiusLat2 * 2);
		map2.put("maxLng", maxLatAndLog[3] - intervalRadiusLon2);
		map2.put("latitude", lat - intervalRadiusLat2);
		map2.put("longitude", lon);
		List<MomentDTO> moments2 = momentMapper.findWithtThumbsUpCount(map2);
		MomentDTO momentDTO2 = null;
		if (!CommonUtil.isNullOrEmpty(moments2)) {
			momentDTO2 = moments2.get(0);
			momentDTO2.setCount(moments2.size());
			moments.add(momentDTO2);
		}

		Map<String, Object> map3 = new HashMap<String, Object>();// 中心点下面的一点的左边一点
		map3.put("minLat", maxLatAndLog[0]);
		map3.put("minLng", maxLatAndLog[1]);
		map3.put("maxLat", maxLatAndLog[2] - intervalRadiusLat2 * 2);
		map3.put("maxLng", maxLatAndLog[3] - intervalRadiusLon2 * 2);
		map3.put("latitude", lat - intervalRadiusLat2);
		map3.put("longitude", lon - intervalRadiusLon2);
		List<MomentDTO> moments3 = momentMapper.findWithtThumbsUpCount(map3);
		MomentDTO momentDTO3 = null;
		if (!CommonUtil.isNullOrEmpty(moments3)) {
			momentDTO3 = moments3.get(0);
			momentDTO3.setCount(moments3.size());
			moments.add(momentDTO3);
		}

		Map<String, Object> map4 = new HashMap<String, Object>();// 中心点下面的一点的右边一点
		map4.put("minLat", maxLatAndLog[0]);
		map4.put("minLng", maxLatAndLog[1] + intervalRadiusLon2 * 2);
		map4.put("maxLat", maxLatAndLog[2] - intervalRadiusLat2 * 2);
		map4.put("maxLng", maxLatAndLog[3]);
		map4.put("latitude", lat - intervalRadiusLat2);
		map4.put("longitude", lon + intervalRadiusLon2);
		List<MomentDTO> moments4 = momentMapper.findWithtThumbsUpCount(map4);
		MomentDTO momentDTO4 = null;
		if (!CommonUtil.isNullOrEmpty(moments4)) {
			momentDTO4 = moments4.get(0);
			momentDTO4.setCount(moments4.size());
			moments.add(momentDTO4);
		}

		Map<String, Object> map5 = new HashMap<String, Object>();// 中心点左边一点
		map5.put("minLat", maxLatAndLog[0] + intervalRadiusLat2);
		map5.put("minLng", maxLatAndLog[1]);
		map5.put("maxLat", maxLatAndLog[2] - intervalRadiusLat2);
		map5.put("maxLng", maxLatAndLog[3] - intervalRadiusLon2 * 2);
		map5.put("latitude", lat);
		map5.put("longitude", lon - intervalRadiusLon2);
		List<MomentDTO> moments5 = momentMapper.findWithtThumbsUpCount(map5);
		MomentDTO momentDTO5 = null;
		if (!CommonUtil.isNullOrEmpty(moments5)) {
			momentDTO5 = moments5.get(0);
			momentDTO5.setCount(moments5.size());
			moments.add(momentDTO5);
		}

		Map<String, Object> map6 = new HashMap<String, Object>();// 中心点右边一点
		map6.put("minLat", maxLatAndLog[0] + intervalRadiusLat2);
		map6.put("minLng", maxLatAndLog[1] + intervalRadiusLon2 * 2);
		map6.put("maxLat", maxLatAndLog[2] - intervalRadiusLat2);
		map6.put("maxLng", maxLatAndLog[3]);
		map6.put("latitude", lat);
		map6.put("longitude", lon + intervalRadiusLon2);
		List<MomentDTO> moments6 = momentMapper.findWithtThumbsUpCount(map6);
		MomentDTO momentDTO6 = null;
		if (!CommonUtil.isNullOrEmpty(moments6)) {
			momentDTO6 = moments6.get(0);
			momentDTO6.setCount(moments6.size());
			moments.add(momentDTO6);
		}

		Map<String, Object> map7 = new HashMap<String, Object>();// 中心点上面一点
		map7.put("minLat", maxLatAndLog[0] + intervalRadiusLat2 * 2);
		map7.put("minLng", maxLatAndLog[1] + intervalRadiusLon2);
		map7.put("maxLat", maxLatAndLog[2]);
		map7.put("maxLng", maxLatAndLog[3] - intervalRadiusLon2);
		map7.put("latitude", lat + intervalRadiusLat2);
		map7.put("longitude", lon);
		List<MomentDTO> moments7 = momentMapper.findWithtThumbsUpCount(map7);
		MomentDTO momentDTO7 = null;
		if (!CommonUtil.isNullOrEmpty(moments7)) {
			momentDTO7 = moments7.get(0);
			momentDTO7.setCount(moments7.size());
			moments.add(momentDTO7);
		}

		Map<String, Object> map8 = new HashMap<String, Object>();// 中心点上面的左边一点
		map8.put("minLat", maxLatAndLog[0] + intervalRadiusLat2 * 2);
		map8.put("minLng", maxLatAndLog[1]);
		map8.put("maxLat", maxLatAndLog[2]);
		map8.put("maxLng", maxLatAndLog[3] - intervalRadiusLon2 * 2);
		map8.put("latitude", lat + intervalRadiusLat2);
		map8.put("longitude", lon - intervalRadiusLon2);
		List<MomentDTO> moments8 = momentMapper.findWithtThumbsUpCount(map8);
		MomentDTO momentDTO8 = null;
		if (!CommonUtil.isNullOrEmpty(moments8)) {
			momentDTO8 = moments8.get(0);
			momentDTO8.setCount(moments8.size());
			moments.add(momentDTO8);
		}

		Map<String, Object> map9 = new HashMap<String, Object>();// 中心点上面的右边一点
		map9.put("minLat", maxLatAndLog[0] + intervalRadiusLat2 * 2);
		map9.put("minLng", maxLatAndLog[1] + intervalRadiusLon2 * 2);
		map9.put("maxLat", maxLatAndLog[2]);
		map9.put("maxLng", maxLatAndLog[3]);
		map9.put("latitude", lat + intervalRadiusLat2);
		map9.put("longitude", lon + intervalRadiusLon2);
		List<MomentDTO> moments9 = momentMapper.findWithtThumbsUpCount(map9);
		MomentDTO momentDTO9 = null;
		if (!CommonUtil.isNullOrEmpty(moments9)) {
			momentDTO9 = moments9.get(0);
			momentDTO9.setCount(moments9.size());
			moments.add(momentDTO9);
		}
		return moments;
	}
}
