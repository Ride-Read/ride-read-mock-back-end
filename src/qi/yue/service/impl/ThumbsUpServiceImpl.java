package qi.yue.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import qi.yue.dao.mapper.ThumbsUpMapper;
import qi.yue.entity.ThumbsUp;
import qi.yue.service.ThumbsUpService;

@Service
public class ThumbsUpServiceImpl implements ThumbsUpService {
	@Resource
	private ThumbsUpMapper thumbsUpMapper;

	@Override
	public int save(ThumbsUp thumbsUp) {
		return thumbsUpMapper.insert(thumbsUp);
	}

	@Override
	public int delete(int id) {
		return thumbsUpMapper.delete(id);
	}
}
