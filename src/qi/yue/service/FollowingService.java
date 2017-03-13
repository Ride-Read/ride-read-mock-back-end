package qi.yue.service;

import java.util.List;

import qi.yue.dto.FollowingDto;
import qi.yue.entity.Follower;
import qi.yue.entity.Following;

public interface FollowingService {
	List<FollowingDto> findByTid(int tid);

	List<FollowingDto> findByFid(int fid);

	int save(Following following);

	int saveFollowingAndFollower(Following following, Follower follower);

	public int deleteFollowingAndFollower(int fid, int tid);

	int deleteByFid(int fid);
}
