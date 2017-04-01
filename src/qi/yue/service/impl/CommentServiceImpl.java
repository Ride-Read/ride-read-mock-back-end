package qi.yue.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import qi.yue.common.MessageCommon;
import qi.yue.dao.mapper.CommentMapper;
import qi.yue.dto.CommentDTO;
import qi.yue.entity.Comment;
import qi.yue.exception.BusinessException;
import qi.yue.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Resource
	private CommentMapper commentMapper;

	@Override
	public int save(Comment comment) throws BusinessException{
		try {
			return commentMapper.insert(comment);	
		}catch(BusinessException e) {
			throw new BusinessException(MessageCommon.STATUS_SAVE_FAIL,
					MessageCommon.FAIL_MESSAGE_SAVE_FAIL);
		}
		
	}

	@Override
	public int delete(Integer id) {
		return commentMapper.delete(id);
	}

	@Override
	public CommentDTO find(Integer id) {
		// TODO Auto-generated method stub
		return commentMapper.find(id);
	}
}
