package qi.yue.service;

import java.util.List;

import qi.yue.dto.FollowerDTO;
import qi.yue.entity.Follower;

public interface FollowerService {
	List<FollowerDTO> findByTid(int tid);

	List<FollowerDTO> findByFid(int fid);

	int save(Follower follower);

	int deleteByFid(int fid);

}
