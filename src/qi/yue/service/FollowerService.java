package qi.yue.service;

import java.util.List;

import qi.yue.entity.Follower;

public interface FollowerService {
	List<Follower> findByTid(int tid);

	List<Follower> findByFid(int fid);

	int save(Follower follower);

	int deleteByFid(int fid);

}
