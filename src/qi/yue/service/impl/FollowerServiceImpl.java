package qi.yue.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import qi.yue.dao.mapper.FollowerMapper;
import qi.yue.dto.FollowerDto;
import qi.yue.entity.Follower;
import qi.yue.service.FollowerService;

@Service
public class FollowerServiceImpl implements FollowerService {
	@Resource
	private FollowerMapper followerMapper;

	@Override
	public List<FollowerDto> findByTid(int tid) {
		return followerMapper.findByTid(tid);
	}

	@Override
	public int save(Follower follower) {
		return followerMapper.insert(follower);
	}

	@Override
	public List<FollowerDto> findByFid(int fid) {
		return followerMapper.findByFid(fid);
	}

	@Override
	public int deleteByFid(int fid) {
		return followerMapper.deleteByFid(fid);
	}
}
