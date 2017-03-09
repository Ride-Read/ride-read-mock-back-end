package qi.yue.service;

import java.util.List;

import qi.yue.entity.Following;

public interface MomentService {
	List<Following> findByTid(int tid);
}
