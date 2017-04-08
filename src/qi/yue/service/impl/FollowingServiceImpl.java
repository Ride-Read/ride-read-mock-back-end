package qi.yue.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qi.yue.common.MessageCommon;
import qi.yue.dao.mapper.FollowerMapper;
import qi.yue.dao.mapper.FollowingMapper;
import qi.yue.dto.FollowingDTO;
import qi.yue.dto.UserDTO;
import qi.yue.entity.Follower;
import qi.yue.entity.Following;
import qi.yue.exception.BusinessException;
import qi.yue.exception.ParameterException;
import qi.yue.service.FollowingService;
import qi.yue.service.UserService;
import qi.yue.utils.CommonUtil;

@Service
public class FollowingServiceImpl implements FollowingService {

	@Resource
	private FollowerMapper followerMapper;

	@Resource
	private FollowingMapper followingMapper;

	@Resource
	private UserService userService;

	@Override
	public List<FollowingDTO> findByTid(int tid) {
		return followingMapper.findByTid(tid);
	}

	@Override
	public int save(Following following) {
		return followingMapper.insert(following);
	}

	/**
	 * 将粉丝信息和被粉人信息写入
	 * 
	 * @param following
	 * @param follower
	 * @return int
	 * @throws BusinessException
	 */
	@Transactional
	public int saveFollowingAndFollower(Following following, Follower follower) throws BusinessException {
		try {

			int countFollowing = followingMapper.insert(following);
			int countFollower = followerMapper.insert(follower);

			return (countFollowing == 1 && countFollower == 1) ? 1 : 0;

		} catch (BusinessException e) {
			throw new BusinessException(MessageCommon.STATUS_SAVE_FAIL, MessageCommon.FAIL_MESSAGE_SAVE_FAIL);
		}
	}

	/**
	 * 查询用户粉丝列表
	 * 
	 * @param fid
	 * @return List<FollowingDTO>
	 */
	private List<FollowingDTO> findByFid(int fid) {
		try {
			return followingMapper.findByFid(fid);
		} catch (BusinessException e) {
			throw new BusinessException(MessageCommon.STATUS_QUERY_FAIL, MessageCommon.FAIL_MESSAGE_QUERY_FAIL);
		}

	}

	@Override
	public int deleteByFid(int fid) {
		return followingMapper.deleteByFid(fid);
	}

	/**
	 * 删除粉丝人信息与被粉信息
	 * 
	 * @param fid
	 * @param tid
	 * @return int
	 */
	public int deleteFollowingAndFollower(int fid, int tid) throws BusinessException {
		try {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("fid", fid);
			map.put("tid", tid);
			int countFollowing = followingMapper.deleteByFidAndTid(map);
			int countFollower = followerMapper.deleteByFidAndTid(map);

			return (countFollowing == 1 && countFollower == 1) ? 1 : 0;

		} catch (BusinessException e) {
			throw new BusinessException(MessageCommon.STATUS_DELETE_FAIL, MessageCommon.FAIL_MESSAGE_DELETE_FAIL);
		}
	}

	@Override
	public List<FollowingDTO> queryFollowing(Integer uid, String token, Long timestamp)
			throws ParameterException, BusinessException {
		if (CommonUtil.isNull(uid) || CommonUtil.isNullOrEmpty(token) || CommonUtil.isNullOrEmpty(timestamp)) {
			throw new ParameterException();
		}

		List<FollowingDTO> dtoList = findByFid(uid);
		return dtoList;
	}

	@Override
	public void follow(Integer uid, String token, Integer user_id, Long timestamp)
			throws ParameterException, BusinessException {
		if (CommonUtil.isNull(uid) || CommonUtil.isNullOrEmpty(token) || CommonUtil.isNullOrEmpty(user_id)
				|| CommonUtil.isNullOrEmpty(timestamp)) {
			throw new ParameterException();
		}
		UserDTO userFollowed = userService.find(user_id);
		UserDTO userFollowing = userService.find(uid);

		if (CommonUtil.isNull(userFollowed) || CommonUtil.isNull(userFollowing)) {
			throw new BusinessException(MessageCommon.STATUS_USER_NOT_EXIST, MessageCommon.FAIL_MESSAGE_USER_NOT_EXIST);
		}

		Following following = new Following();
		following.setFid(uid);
		following.setTid(user_id);
		following.setFaceUrl(userFollowed.getFace_url());
		following.setSignature(userFollowed.getSignature());
		// following.setNickname(userFollowed.getNickname());
		following.setCreatedAt(new Date());
		following.setUpdatedAt(new Date());

		Follower follower = new Follower();
		follower.setFid(uid);
		follower.setTid(user_id);
		follower.setFaceUrl(userFollowing.getFace_url());
		follower.setSignature(userFollowing.getSignature());
		// follower.setNickname(userFollowing.getNickname());
		follower.setCreatedAt(new Date());
		follower.setUpdatedAt(new Date());

		saveFollowingAndFollower(following, follower);
	}

	@Override
	public void unfollow(Integer uid, String token, Integer user_id, Long timestamp)
			throws ParameterException, BusinessException {
		if (CommonUtil.isNull(uid) || CommonUtil.isNullOrEmpty(token) || CommonUtil.isNull(user_id)
				|| CommonUtil.isNullOrEmpty(timestamp)) {
			throw new ParameterException();
		}

		deleteFollowingAndFollower(uid, user_id);

	}
}
