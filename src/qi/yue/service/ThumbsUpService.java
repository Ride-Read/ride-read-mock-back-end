package qi.yue.service;

import qi.yue.entity.ThumbsUp;

public interface ThumbsUpService {
	int save(ThumbsUp thumbsUp);

	int delete(int thumbsUpId);
}
