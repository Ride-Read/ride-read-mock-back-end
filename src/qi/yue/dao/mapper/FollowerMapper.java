package qi.yue.dao.mapper;

import java.util.List;
import java.util.Map;

import qi.yue.entity.Follower;

public interface FollowerMapper {
	List<Follower> findByTid(int tid);

	List<Follower> findByFid(int fid);

	int insert(Follower follower);

	int deleteByFid(int fid);

	int deleteByFidAndTid(Map<String, Integer> map);
}