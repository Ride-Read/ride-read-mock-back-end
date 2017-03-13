package qi.yue.service;

import java.util.List;

import qi.yue.entity.Follower;
import qi.yue.entity.Following;

public interface FollowingService {
	List<Following> findByTid(int tid);

	List<Following> findByFid(int fid);

	int save(Following following);

	int saveFollowingAndFollower(Following following, Follower follower);

	public int deleteFollowingAndFollower(int fid, int tid);

	int deleteByFid(int fid);
}
