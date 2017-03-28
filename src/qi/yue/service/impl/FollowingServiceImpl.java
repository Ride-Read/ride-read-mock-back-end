package qi.yue.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qi.yue.dao.mapper.FollowerMapper;
import qi.yue.dao.mapper.FollowingMapper;
import qi.yue.dto.FollowingDTO;
import qi.yue.entity.Follower;
import qi.yue.entity.Following;
import qi.yue.service.FollowingService;

@Service
public class FollowingServiceImpl implements FollowingService {
	@Resource
	private FollowerMapper followerMapper;
	@Resource
	private FollowingMapper followingMapper;

	@Override
	public List<FollowingDTO> findByTid(int tid) {
		return followingMapper.findByTid(tid);
	}

	@Override
	public int save(Following following) {
		return followingMapper.insert(following);
	}

	@Override
	@Transactional
	public int saveFollowingAndFollower(Following following, Follower follower) {
		int countFollowing = followingMapper.insert(following);
		int countFollower = followerMapper.insert(follower);
		int count = 0;
		if (countFollowing == 1 && countFollower == 1)
			count = 1;
		return count;
	}

	@Override
	public List<FollowingDTO> findByFid(int fid) {
		return followingMapper.findByFid(fid);
	}

	@Override
	public int deleteByFid(int fid) {
		return followingMapper.deleteByFid(fid);
	}

	@Override
	public int deleteFollowingAndFollower(int fid, int tid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fid", fid);
		map.put("tid", tid);
		int countFollowing = followingMapper.deleteByFidAndTid(map);
		int countFollower = followerMapper.deleteByFidAndTid(map);
		int count = 0;
		if (countFollowing == 1 && countFollower == 1)
			count = 1;
		return count;
	}
}
