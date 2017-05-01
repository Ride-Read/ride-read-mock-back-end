package qi.yue.dao.mapper;

import java.util.List;

import qi.yue.dto.CommentDTO;
import qi.yue.entity.Comment;

public interface CommentMapper {
	int insert(Comment record);

	int delete(Integer id);

	int deleteByMid(Integer mid);

	CommentDTO find(Integer id);

	List<CommentDTO> findByMomentId(Integer momentId);
}