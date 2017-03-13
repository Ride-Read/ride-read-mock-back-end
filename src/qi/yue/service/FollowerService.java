package qi.yue.service;

import java.util.List;

import qi.yue.dto.FollowerDto;
import qi.yue.entity.Follower;

public interface FollowerService {
	List<FollowerDto> findByTid(int tid);

	List<FollowerDto> findByFid(int fid);

	int save(Follower follower);

	int deleteByFid(int fid);

}
