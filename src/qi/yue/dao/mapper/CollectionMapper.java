package qi.yue.dao.mapper;

import java.util.List;
import java.util.Map;

import qi.yue.dto.CollectionDTO;
import qi.yue.entity.Collection;

public interface CollectionMapper {
	int insert(Collection collection);

	CollectionDTO findIdAndMid(Map<String, Object> map);

	List<CollectionDTO> findCollectionsByUid(Integer uid);
}