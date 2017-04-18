package qi.yue.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qi.yue.common.MessageCommon;
import qi.yue.dao.mapper.FollowMapper;
import qi.yue.dao.mapper.UserMapper;
import qi.yue.dto.FollowDTO;
import qi.yue.dto.UserDTO;
import qi.yue.entity.Follow;
import qi.yue.entity.User;
import qi.yue.exception.BusinessException;
import qi.yue.exception.ParameterException;
import qi.yue.service.FollowService;
import qi.yue.utils.CommonUtil;

@Service
public class FollowServiceImpl implements FollowService {
	@Resource
	private FollowMapper followMapper;
	@Resource
	private UserMapper userMapper;

	public Integer isFollow(Integer uid, Integer user_id) {
		Map<String, Object> mapTemp1 = new HashMap<String, Object>();
		mapTemp1.put("fid", uid);
		mapTemp1.put("tid", user_id);
		FollowDTO followDTO1 = followMapper.findByFidAndTid(mapTemp1);

		Map<String, Object> mapTemp2 = new HashMap<String, Object>();
		mapTemp2.put("fid", user_id);
		mapTemp2.put("tid", uid);
		FollowDTO followDTO2 = followMapper.findByFidAndTid(mapTemp1);
		if (CommonUtil.isNull(followDTO1) && CommonUtil.isNull(followDTO2)) {
			return -1;
		} else if (!CommonUtil.isNull(followDTO1) && !CommonUtil.isNull(followDTO2)) {
			return 0;
		} else if (!CommonUtil.isNull(followDTO1)) {
			return 1;
		} else {
			return 2;
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public void follow(Integer uid, String token, Integer user_id, Long timestamp)
			throws ParameterException, BusinessException {
		if (CommonUtil.isNull(uid) || CommonUtil.isNullOrEmpty(token) || CommonUtil.isNullOrEmpty(user_id)
				|| CommonUtil.isNullOrEmpty(timestamp)) {
			throw new ParameterException();
		}
		UserDTO userFollowed = userMapper.find(user_id);
		UserDTO userFollowing = userMapper.find(uid);

		if (CommonUtil.isNull(userFollowed) || CommonUtil.isNull(userFollowing)) {
			throw new BusinessException(MessageCommon.STATUS_USER_NOT_EXIST, MessageCommon.FAIL_MESSAGE_USER_NOT_EXIST);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fid", uid);
		map.put("tid", user_id);
		FollowDTO followDTO = followMapper.findByFidAndTid(map);
		if (CommonUtil.isNullOrEmpty(followDTO)) {
			Follow follow = new Follow();
			follow.setFid(uid);
			follow.setTid(user_id);
			follow.setFollowerFaceUrl(userFollowing.getFace_url());
			follow.setFollowerSignature(userFollowing.getSignature());
			follow.setFollowerUsername(userFollowing.getUsername());

			follow.setFollowedFaceUrl(userFollowed.getFace_url());
			follow.setFollowedSignature(userFollowed.getSignature());
			follow.setFollowedUsername(userFollowed.getUsername());
			follow.setCreatedAt(new Date());
			follow.setUpdatedAt(new Date());
			followMapper.insert(follow);

			Integer follower = userFollowed.getFollower() + 1;
			User usered = new User();
			usered.setId(userFollowed.getUid());
			usered.setFollower(follower);
			userMapper.update(usered);

			Integer following = userFollowing.getFollowing() + 1;
			User usering = new User();
			usering.setId(userFollowing.getUid());
			usering.setFollowing(following);
			userMapper.update(usering);
		} else {
			throw new BusinessException(MessageCommon.STATUS_REPEAT_FOLLOW, MessageCommon.FAIL_MESSAGE_REPEAT_FOLLOW);
		}
	}

	@Override
	public List<FollowDTO> queryFollower(Integer tid, String token, Long timestamp)
			throws ParameterException, BusinessException {
		if (CommonUtil.isNull(tid) || CommonUtil.isNullOrEmpty(token) || CommonUtil.isNullOrEmpty(timestamp)) {
			throw new ParameterException();
		}
		try {
			return followMapper.findByTid(tid);
		} catch (BusinessException e) {
			throw new BusinessException(MessageCommon.STATUS_QUERY_FAIL, MessageCommon.FAIL_MESSAGE_QUERY_FAIL);
		}
	}

	@Override
	public List<FollowDTO> queryFollowed(Integer fid, String token, Long timestamp)
			throws ParameterException, BusinessException {
		if (CommonUtil.isNull(fid) || CommonUtil.isNullOrEmpty(token) || CommonUtil.isNullOrEmpty(timestamp)) {
			throw new ParameterException();
		}
		try {
			return followMapper.findByFid(fid);
		} catch (BusinessException e) {
			throw new BusinessException(MessageCommon.STATUS_QUERY_FAIL, MessageCommon.FAIL_MESSAGE_QUERY_FAIL);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void unfollow(Integer fid, String token, Integer user_id, Long timestamp)
			throws ParameterException, BusinessException {
		if (CommonUtil.isNull(fid) || CommonUtil.isNullOrEmpty(token) || CommonUtil.isNull(user_id)
				|| CommonUtil.isNullOrEmpty(timestamp)) {
			throw new ParameterException();
		}
		UserDTO userFollowed = userMapper.find(user_id);
		UserDTO userFollowing = userMapper.find(fid);
		if (CommonUtil.isNull(userFollowed) || CommonUtil.isNull(userFollowing)) {
			throw new BusinessException(MessageCommon.STATUS_USER_NOT_EXIST, MessageCommon.FAIL_MESSAGE_USER_NOT_EXIST);
		}
		try {
			followMapper.deleteByFid(fid);
			Integer follower = userFollowed.getFollower() - 1;
			User usered = new User();
			usered.setId(userFollowed.getUid());
			usered.setFollower(follower);
			userMapper.update(usered);

			Integer following = userFollowed.getFollowing() - 1;
			User usering = new User();
			usering.setId(userFollowing.getUid());
			usering.setFollowing(following);
			userMapper.update(usering);
		} catch (BusinessException e) {
			throw new BusinessException(MessageCommon.STATUS_DELETE_FAIL, MessageCommon.FAIL_MESSAGE_DELETE_FAIL);
		}

	}

	/**
	 * 查询关注该用户的所有用户列表
	 * 
	 * @param tid
	 * @return List<FollowerDTO>
	 */
	private List<FollowDTO> findByTid(Integer tid) throws BusinessException {
		try {
			return followMapper.findByTid(tid);
		} catch (BusinessException e) {
			throw new BusinessException(MessageCommon.STATUS_QUERY_FAIL, MessageCommon.FAIL_MESSAGE_QUERY_FAIL);
		}

	}

	@Override
	public Map<String, Object> searchFollowerOrFollowing(Integer uid, String token, Long timestamp, String shortname)
			throws ParameterException, BusinessException {
		if (CommonUtil.isNullOrEmpty(uid) || CommonUtil.isNullOrEmpty(token) || CommonUtil.isNullOrEmpty(timestamp)
				|| CommonUtil.isNullOrEmpty(shortname)) {
			throw new ParameterException();
		}
		Map<String, Object> map = new HashMap<>();
		map.put("uid", uid);
		map.put("shortname", shortname);
		List<FollowDTO> followers = followMapper.searchFollower(map);
		List<FollowDTO> followeds = followMapper.searchFollowing(map);
		Map<String, Object> data = new HashMap<>();
		data.put("followers", followers);
		data.put("followeds", followeds);
		return data;
	}

	@Override
	public FollowDTO findByFidAndTid(Map<String, Object> map) {
		return followMapper.findByFidAndTid(map);
	}
}
