package qi.yue.service;

import qi.yue.dto.CommentDTO;
import qi.yue.entity.Comment;
import qi.yue.exception.BusinessException;

public interface CommentService {
	int save(Comment comment) throws BusinessException;

	CommentDTO find(Integer id);

	int delete(Integer id);
}
