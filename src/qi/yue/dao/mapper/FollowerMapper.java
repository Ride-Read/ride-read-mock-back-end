package qi.yue.dao.mapper;

import java.util.List;

import qi.yue.entity.Follower;

public interface FollowerMapper {
	List<Follower> findByTid(int tid);
}