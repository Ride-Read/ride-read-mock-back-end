package qi.yue.dao.mapper;

import java.util.List;
import java.util.Map;

import qi.yue.dto.FollowerDto;
import qi.yue.entity.Follower;

public interface FollowerMapper {
	List<FollowerDto> findByTid(int tid);

	List<FollowerDto> findByFid(int fid);

	int insert(Follower follower);

	int deleteByFid(int fid);

	int deleteByFidAndTid(Map<String, Integer> map);
}