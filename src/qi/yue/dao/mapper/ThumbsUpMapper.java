package qi.yue.dao.mapper;

import java.util.List;
import java.util.Map;

import qi.yue.dto.ThumbsUpDTO;
import qi.yue.entity.ThumbsUp;

public interface ThumbsUpMapper {

	int insert(ThumbsUp thumbsUp);

	List<ThumbsUpDTO> findThumbsUpByMid(Map<String, Object> map);

	List<ThumbsUpDTO> findByMomentId(Integer momentId);

	int delete(Integer id);

	ThumbsUpDTO findByMidAndUid(Map<String, Object> map);
}