package qi.yue.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import qi.yue.common.MessageCommon;
import qi.yue.dao.mapper.FollowMapper;
import qi.yue.dao.mapper.FollowerMapper;
import qi.yue.dao.mapper.UserMapper;
import qi.yue.dto.FollowDTO;
import qi.yue.dto.FollowerDTO;
import qi.yue.dto.UserDTO;
import qi.yue.dto.assembler.FollowDTOAssembler;
import qi.yue.entity.Follow;
import qi.yue.entity.Follower;
import qi.yue.entity.Following;
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

	@Resource
	private FollowerMapper followerMapper;

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
	public void unfollow(Integer fid, String token, Integer user_id, Long timestamp)
			throws ParameterException, BusinessException {
		if (CommonUtil.isNull(fid) || CommonUtil.isNullOrEmpty(token) || CommonUtil.isNull(user_id)
				|| CommonUtil.isNullOrEmpty(timestamp)) {
			throw new ParameterException();
		}
		try {
			followMapper.deleteByFid(fid);
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
	public int save(Follower follower) {
		return followerMapper.insert(follower);
	}

	@Override
	public List<FollowerDTO> findByFid(int fid) {
		return followerMapper.findByFid(fid);
	}

	@Override
	public int deleteByFid(int fid) {
		return followerMapper.deleteByFid(fid);
	}

}
