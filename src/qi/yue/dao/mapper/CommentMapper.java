package qi.yue.dao.mapper;

import qi.yue.dto.CommentDto;
import qi.yue.entity.Comment;

public interface CommentMapper {
	int insert(Comment record);

	int delete(Integer id);

	CommentDto find(Integer id);
}