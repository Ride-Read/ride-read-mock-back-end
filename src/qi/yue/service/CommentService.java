package qi.yue.service;

import qi.yue.dto.CommentDto;
import qi.yue.entity.Comment;

public interface CommentService {
	int save(Comment comment);

	CommentDto find(Integer id);

	int delete(Integer id);
}
