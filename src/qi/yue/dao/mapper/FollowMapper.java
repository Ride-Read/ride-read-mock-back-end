package qi.yue.dao.mapper;

import java.util.List;

import qi.yue.dto.FollowDTO;
import qi.yue.entity.Follow;

public interface FollowMapper {
	int insert(Follow follow);

	List<FollowDTO> findByTid(Integer tid);

	List<FollowDTO> findByFid(Integer fid);

	int deleteByFid(Integer fid);
}