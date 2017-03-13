package qi.yue.dao.mapper;

import java.util.List;
import java.util.Map;

import qi.yue.entity.Following;

public interface FollowingMapper {
	List<Following> findByTid(int tid);

	List<Following> findByFid(int fid);

	int insert(Following following);

	int deleteByFid(int fid);

	int deleteByFidAndTid(Map<String, Integer> map);
}