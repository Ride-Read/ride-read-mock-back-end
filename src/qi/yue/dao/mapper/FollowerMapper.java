package qi.yue.dao.mapper;

import java.util.List;
import java.util.Map;

import qi.yue.dto.FollowerDTO;
import qi.yue.entity.Follower;

public interface FollowerMapper {
	List<FollowerDTO> findByTid(int tid);

	List<FollowerDTO> findByFid(int fid);

	int insert(Follower follower);

	int deleteByFid(int fid);

	int deleteByFidAndTid(Map<String, Object> map);
}