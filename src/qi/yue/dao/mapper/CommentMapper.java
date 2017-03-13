package qi.yue.dao.mapper;

import qi.yue.entity.Comment;

public interface CommentMapper {
	int insert(Comment record);

	int deleteByPrimaryKey(Integer id);
}