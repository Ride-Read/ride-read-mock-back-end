package qi.yue.dao.mapper;

import java.util.List;

import qi.yue.entity.Following;

public interface FollowingMapper {
	List<Following> findByTid(int tid);
}