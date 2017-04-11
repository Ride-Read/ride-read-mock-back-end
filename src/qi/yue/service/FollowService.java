package qi.yue.service;

import java.util.List;
import java.util.Map;

import qi.yue.dto.FollowDTO;
import qi.yue.dto.FollowerDTO;
import qi.yue.entity.Follow;
import qi.yue.entity.Follower;
import qi.yue.exception.BusinessException;
import qi.yue.exception.ParameterException;

public interface FollowService {
	// List<FollowerDTO> findByTid(int tid);

	List<FollowerDTO> findByFid(int fid);

	int save(Follower follower);

	int deleteByFid(int fid);

	/**
	 * 查询所有关注该用户的粉丝
	 * 
	 * @param uid
	 * @param token
	 * @param timestamp
	 * @return List<FollowerDTO>
	 * @throws ParameterException
	 * @throws BusinessException
	 */
	public List<FollowDTO> queryFollower(Integer uid, String token, Long timestamp)
			throws ParameterException, BusinessException;

	void follow(Integer uid, String token, Integer user_id, Long timestamp);

	public List<FollowDTO> queryFollowed(Integer fid, String token, Long timestamp)
			throws ParameterException, BusinessException;

	public void unfollow(Integer uid, String token, Integer user_id, Long timestamp)
			throws ParameterException, BusinessException;

	public Map<String, Object> searchFollowerOrFollowing(Integer uid, String token, Long timestamp, String shortname);
}
