package qi.yue.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qi.yue.dao.mapper.ThumbsUpMapper;
import qi.yue.entity.ThumbsUp;
import qi.yue.service.ThumbsUpService;

@Service
public class ThumbsUpServiceImpl implements ThumbsUpService {
	@Resource
	private ThumbsUpMapper thumbsUpMapper;

	@Override
	@Transactional
	public int save(ThumbsUp thumbsUp) {
		return thumbsUpMapper.insert(thumbsUp);
	}

	@Override
	@Transactional
	public int delete(int id) {
		return thumbsUpMapper.delete(id);
	}
}
