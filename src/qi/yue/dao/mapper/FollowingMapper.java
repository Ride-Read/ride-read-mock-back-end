package qi.yue.dao.mapper;

import java.util.List;
import java.util.Map;

import qi.yue.dto.FollowingDto;
import qi.yue.entity.Following;

public interface FollowingMapper {
	List<FollowingDto> findByTid(int tid);

	List<FollowingDto> findByFid(int fid);

	int insert(Following following);

	int deleteByFid(int fid);

	int deleteByFidAndTid(Map<String, Integer> map);
}