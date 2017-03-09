package qi.yue.service;

import qi.yue.entity.Comment;

public interface CommentService {
	int save(Comment comment);
	int deleteByPrimaryKey(Integer commentId);
}
