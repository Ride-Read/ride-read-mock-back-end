package qi.yue.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import qi.yue.entity.Moment;
import qi.yue.dao.mapper.MomentMapper;
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
	public Moment find(int id) {
		return momentMapper.find(id);
	}

	@Override
	public List<Moment> findByUserId(int userId) {
		return momentMapper.findByUserId(userId);
	}

	@Override
	public Map<String, Object> showMoment(int userId) {
		return null;
	}
}
