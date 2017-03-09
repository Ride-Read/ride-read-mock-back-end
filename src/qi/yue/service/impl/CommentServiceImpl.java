package qi.yue.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import qi.yue.dao.mapper.CommentMapper;
import qi.yue.entity.Comment;
import qi.yue.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Resource
	private CommentMapper commentMapper;

	@Override
	public int save(Comment comment) {
		return commentMapper.insert(comment);
	}

	@Override
	public int deleteByPrimaryKey(Integer commentId) {
		return commentMapper.deleteByPrimaryKey(commentId);
	}
}
