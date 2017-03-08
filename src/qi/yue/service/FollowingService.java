package qi.yue.service;

import java.util.List;

import qi.yue.entity.Following;

public interface FollowingService {
	List<Following> findByTid(int tid);
}
