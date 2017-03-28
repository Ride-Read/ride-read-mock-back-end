package qi.yue.dao.mapper;

import java.util.List;
import java.util.Map;

import qi.yue.dto.FollowingDTO;
import qi.yue.entity.Following;

public interface FollowingMapper {
	List<FollowingDTO> findByTid(int tid);

	List<FollowingDTO> findByFid(int fid);

	FollowingDTO findByFidAndTid(Map<String, Object> map);

	int insert(Following following);

	int deleteByFid(int fid);

	int deleteByFidAndTid(Map<String, Object> map);
}