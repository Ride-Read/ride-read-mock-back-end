package qi.yue.service;

import java.util.List;

import qi.yue.dto.FollowingDTO;
import qi.yue.entity.Follower;
import qi.yue.entity.Following;
import qi.yue.exception.BusinessException;
import qi.yue.exception.ParameterException;

public interface FollowingService {
	List<FollowingDTO> findByTid(int tid);

//	List<FollowingDTO> findByFid(int fid);

	int save(Following following);

//	int saveFollowingAndFollower(Following following, Follower follower) throws BusinessException;

//	public int deleteFollowingAndFollower(int fid, int tid);

	int deleteByFid(int fid);
	
	/**
	 * 查询用户的粉丝信息
	 * @param uid
	 * @param token
	 * @param timestamp
	 * @return List<FollowingDTO>
	 * @throws ParameterException
	 * @throws BusinessException
	 */
	public List<FollowingDTO> queryFollowing(Integer uid, String token, Long timestamp) throws ParameterException, BusinessException;
	
	/**
	 * 点击关注
	 * @param uid
	 * @param token
	 * @param user_id
	 * @param timestamp
	 * @throws ParameterException
	 * @throws BusinessException
	 */
	public void follow(Integer uid, String token, Integer user_id, Long timestamp) throws ParameterException, BusinessException;
	
	/**
	 * 取消关注
	 * @param uid
	 * @param token
	 * @param user_id
	 * @param timestamp
	 * @throws ParameterException
	 * @throws BusinessException
	 */
	public void unfollow(Integer uid, String token, Integer user_id, Long timestamp) throws ParameterException, BusinessException;
}
