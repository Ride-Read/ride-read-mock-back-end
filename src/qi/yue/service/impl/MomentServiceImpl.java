package qi.yue.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import qi.yue.entity.Comment;
import qi.yue.entity.Moment;
import qi.yue.exception.BusinessException;
import qi.yue.exception.ParameterException;
import qi.yue.common.MessageCommon;
import qi.yue.dao.mapper.FollowingMapper;
import qi.yue.dao.mapper.MomentMapper;
import qi.yue.dto.CommentDTO;
import qi.yue.dto.FollowingDTO;
import qi.yue.dto.MomentDTO;
import qi.yue.dto.PageDTO;
import qi.yue.dto.ResponseDTO;
import qi.yue.dto.UserDTO;
import qi.yue.dto.assembler.CommentDtoAssembler;
import qi.yue.service.CommentService;
import qi.yue.service.MomentService;
import qi.yue.service.UserService;
import qi.yue.utils.CommonUtil;
import qi.yue.utils.EncryptionUtil;
import qi.yue.utils.ResponseUtil;
import qi.yue.utils.StringUtil;

@Service
public class MomentServiceImpl implements MomentService {
	@Resource
	private MomentMapper momentMapper;

	@Resource
	private FollowingMapper followingMapper;

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
	public List<MomentDTO> findFollowingsMoment(Map<String, Object> map) throws BusinessException {
		List<MomentDTO> MomentDTOList = momentMapper.findFollowingsMoment(map);
		for (MomentDTO momentDTO : MomentDTOList) {
			Map<String, Object> mapTemp1 = new HashMap<String, Object>();
			mapTemp1.put("fid", map.get("fid"));
			mapTemp1.put("tid", momentDTO.getUid());
			FollowingDTO followingDTO = followingMapper.findByFidAndTid(map);
			if (CommonUtil.isNull(followingDTO)) {
				momentDTO.getUser().setIs_followed(1);
			} else {
				momentDTO.getUser().setIs_followed(0);
			}
			Double distance = momentDTO.getDistance().divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP)
					.doubleValue();
			momentDTO.setDistanceString(distance.toString() + "km");
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
	public List<MomentDTO> findNearbyMoment(Map<String, Object> map) throws BusinessException {
		try {
			List<MomentDTO> MomentDTOList = momentMapper.findNearbyMoment(map);
			for (MomentDTO momentDTO : MomentDTOList) {
				Map<String, Object> mapTemp1 = new HashMap<String, Object>();
				mapTemp1.put("fid", map.get("fid"));
				mapTemp1.put("tid", momentDTO.getUid());
				FollowingDTO followingDTO1 = followingMapper.findByFidAndTid(mapTemp1);

				Map<String, Object> mapTemp2 = new HashMap<String, Object>();
				mapTemp2.put("fid", momentDTO.getUid());
				mapTemp2.put("tid", map.get("fid"));
				FollowingDTO followingDTO2 = followingMapper.findByFidAndTid(mapTemp2);
				if (CommonUtil.isNull(followingDTO1) && CommonUtil.isNull(followingDTO2)) {
					momentDTO.getUser().setIs_followed(-1);
				} else if (!CommonUtil.isNull(followingDTO1) && !CommonUtil.isNull(followingDTO2)) {
					momentDTO.getUser().setIs_followed(1);
				} else {
					momentDTO.getUser().setIs_followed(0);
				}
				Double distance = momentDTO.getDistance().divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP)
						.doubleValue();
				momentDTO.setDistanceString(distance.toString() + "km");
			}
			return MomentDTOList;
		} catch (BusinessException e) {
			throw new BusinessException(MessageCommon.STATUS_QUERY_FAIL, MessageCommon.FAIL_MESSAGE_QUERY_FAIL);
		}

	}

	@Override
	public void postMoment(String msg, Integer uid, String video_url, Integer type, Long timestamp,
			String[] pictures_url, String cover, String token, BigDecimal latitude, BigDecimal longitude)
			throws ParameterException, BusinessException {
		if (CommonUtil.isNullOrEmpty(msg) || CommonUtil.isNull(uid) || CommonUtil.isNull(type)
				|| CommonUtil.isNull(timestamp) || CommonUtil.isNullOrEmpty(token) || CommonUtil.isNull(latitude)
				|| CommonUtil.isNull(longitude)) {
			throw new ParameterException();
		}
		ResponseDTO checkResult = checkType(type, pictures_url, cover, video_url, uid, timestamp, token);
		if (!checkResult.getStatus().equals(MessageCommon.STATUS_SUCCESS)) {
			throw new BusinessException(checkResult.getStatus(), checkResult.getMsg());
		}
		;

		Moment moment = new Moment();
		moment.setMsg(msg);
		moment.setUserId(uid);
		moment.setVideo(video_url);
		moment.setType(type);
		moment.setPictures(StringUtil.arrayToString(pictures_url, ","));
		moment.setCover(cover);
		moment.setLatitude(latitude);
		moment.setLongitude(longitude);
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
		String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp + MessageCommon.PUBLIC_KEY);
		if (!tokenTemp.equals(token)) {
			return ResponseUtil.ConvertToFailResponse();
		}
		return ResponseUtil.ConvertToSuccessResponse();
	}

	@Override
	public Map<String, Object> showUserMoment(Integer user_id, Integer uid, Long timestamp, String token, Integer pages)
			throws ParameterException, BusinessException {
		if (CommonUtil.isNullOrEmpty(user_id) || CommonUtil.isNull(uid) || CommonUtil.isNullOrEmpty(timestamp)
				|| CommonUtil.isNull(pages) || CommonUtil.isNullOrEmpty(token)) {
			throw new ParameterException();
		}
		PageDTO pageDTO = new PageDTO();
		Map<String, Object> result = new HashMap<String, Object>();

		pageDTO.setId(user_id);
		pageDTO.setCurrentNumberFromPages(pages);
		List<MomentDTO> momentDtos = findByPage(pageDTO);
		for (MomentDTO momentDTO : momentDtos) {
			if (!CommonUtil.isNullOrEmpty(momentDTO.getPictureString())) {
				momentDTO.setPictures(momentDTO.getPictureString().split(","));
			}
		}
		UserDTO userDto = userService.find(user_id);
		result.put("user", userDto);
		result.put("moment", momentDtos);
		return result;
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
			if (CommonUtil.isNullOrEmpty(momentDTO.getPictureString())) {
				momentDTO.setPictures(momentDTO.getPictureString().split(","));
			}
		}
		return momentDtos;
	}

	@Override
	public CommentDTO addComment(String msg, Integer mid, Integer uid, Integer reply_uid, String token, Long timestamp)
			throws ParameterException, BusinessException {
		if (CommonUtil.isNullOrEmpty(msg) || CommonUtil.isNullOrEmpty(mid) || CommonUtil.isNull(uid)
				|| CommonUtil.isNullOrEmpty(token) || CommonUtil.isNull(timestamp)) {
			throw new ParameterException();
		}

		UserDTO user = userService.find(uid);
		Comment comment = new Comment();
		comment.setMomentId(mid);
		comment.setUserId(uid);
		comment.setReplyUid(reply_uid);
		comment.setMsg(msg);
		comment.setFaceUrl(user.getFace_url());
		// comment.setNickname(user.getNickname());
		comment.setCreatedAt(new Date());
		commentService.save(comment);
		CommentDTO dto = CommentDtoAssembler.toDto(comment);
		return dto;
	}
}
