package qi.yue.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import qi.yue.entity.Moment;
import qi.yue.dao.mapper.MomentMapper;
import qi.yue.dto.MomentDTO;
import qi.yue.dto.PageDTO;
import qi.yue.service.MomentService;

@Service
public class MomentServiceImpl implements MomentService {
	@Resource
	private MomentMapper momentMapper;

	@Override
	public int save(Moment moment) {
		return momentMapper.insert(moment);
	}

	@Override
	public MomentDTO find(int id) {
		return momentMapper.find(id);
	}

	@Override
	public List<MomentDTO> findByUserId(int userId) {
		return momentMapper.findByUserId(userId);
	}

	@Override
	public int delete(int id) {
		return momentMapper.delete(id);
	}

	@Override
	public List<MomentDTO> findByPage(PageDTO pageDTO) {
		return momentMapper.findByPage(pageDTO);
	}

	@Override
	public List<MomentDTO> findFollowingsMoment(Map<String, Object> map) {
		return momentMapper.findFollowingsMoment(map);
	}

	@Override
	public List<MomentDTO> findNearbyMoment(Map<String, Object> map) {
		return momentMapper.findNearbyMoment(map);
	}

}
