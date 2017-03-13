package qi.yue.dao.mapper;

import qi.yue.entity.ThumbsUp;

public interface ThumbsUpMapper {

	int insert(ThumbsUp thumbsUp);

	int deleteByPrimaryKey(Integer id);
}