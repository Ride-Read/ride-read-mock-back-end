package qi.yue.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import qi.yue.dao.mapper.FollowingMapper;
import qi.yue.entity.Following;
import qi.yue.service.FollowingService;

@Service
public class FollowingServiceImpl implements FollowingService {
	@Resource
	private FollowingMapper followingMapper;

	@Override
	public List<Following> findByTid(int tid) {
		return followingMapper.findByTid(tid);
	}
}
