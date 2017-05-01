package qi.yue.dao.mapper;

import java.util.List;
import java.util.Map;

import qi.yue.dto.FollowDTO;
import qi.yue.entity.Follow;

public interface FollowMapper {
	int insert(Follow follow);

	List<FollowDTO> findByTid(Integer tid);

	List<FollowDTO> findByFid(Integer fid);

	FollowDTO findByFidAndTid(Map<String, Object> map);

	int deleteByFid(Integer fid);

	int deleteByFidAndTid(Map<String, Object> map);

	List<FollowDTO> searchFollower(Map<String, Object> map);

	List<FollowDTO> searchFollowing(Map<String, Object> map);

}