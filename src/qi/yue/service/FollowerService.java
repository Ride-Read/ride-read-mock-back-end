package qi.yue.service;

import java.util.List;

import qi.yue.dto.FollowerDTO;
import qi.yue.entity.Follower;
import qi.yue.exception.BusinessException;
import qi.yue.exception.ParameterException;

public interface FollowerService {
//	List<FollowerDTO> findByTid(int tid);

	List<FollowerDTO> findByFid(int fid);

	int save(Follower follower);

	int deleteByFid(int fid);

	/**
	 * 查询所有关注该用户的粉丝
	 * @param uid
	 * @param token
	 * @param timestamp
	 * @return List<FollowerDTO>
	 * @throws ParameterException
	 * @throws BusinessException
	 */
	public List<FollowerDTO> queryFollower(Integer uid, String token, Long timestamp) throws ParameterException, BusinessException;
}
