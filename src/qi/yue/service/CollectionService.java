package qi.yue.service;

import java.util.List;

import qi.yue.dto.CollectionDTO;
import qi.yue.entity.Collection;

public interface CollectionService {
	int save(Collection collection);

	CollectionDTO save(String token, Integer uid, Integer mid, Integer type, Long timestamp);

	public List<CollectionDTO> showCollections(String token, Integer uid, Long timestamp);
}