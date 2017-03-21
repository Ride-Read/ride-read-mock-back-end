package qi.yue.dao.mapper;

import qi.yue.dto.CommentDTO;
import qi.yue.entity.Comment;

public interface CommentMapper {
	int insert(Comment record);

	int delete(Integer id);

	CommentDTO find(Integer id);
}