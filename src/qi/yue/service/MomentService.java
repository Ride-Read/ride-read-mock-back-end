package qi.yue.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import qi.yue.dto.CommentDTO;
import qi.yue.dto.MomentDTO;
import qi.yue.dto.ThumbsUpDTO;
import qi.yue.exception.BusinessException;
import qi.yue.exception.ParameterException;

public interface MomentService {

	MomentDTO find(int id);

	List<MomentDTO> findByUserId(int userId);

	// List<MomentDTO> findByPage(PageDTO pageDTO);

	int delete(int id);

	// List<MomentDTO> findFollowingsMoment(Map<String, Object> map);

	// List<MomentDTO> findNearbyMoment(Map<String, Object> map);

	/**
	 * 发送悦圈内容
	 * 
	 * @param msg
	 * @param uid
	 * @param video_url
	 * @param type
	 * @param timestamp
	 * @param pictures_url
	 * @param cover
	 * @param token
	 * @param latitude
	 * @param longitude
	 * @throws ParameterException
	 * @throws BusinessException
	 */
	public void postMoment(String msg, Integer uid, String video_url, Integer type, Long timestamp,
			String[] pictures_url, String cover, String token, BigDecimal latitude, BigDecimal longitude)
			throws ParameterException, BusinessException;

	/**
	 * 展示用户悦圈内容
	 * 
	 * @param user_id
	 * @param uid
	 * @param timestamp
	 * @param token
	 * @param pages
	 * @return Map<String, Object>
	 * @throws ParameterException
	 * @throws BusinessException
	 */
	public List<MomentDTO> showUserMoment(Integer user_id, Integer uid, Long timestamp, String token, Integer pages,
			BigDecimal latitude, BigDecimal longitude) throws ParameterException, BusinessException;

	/**
	 * 展示悦圈内容
	 * 
	 * @param uid
	 * @param type
	 * @param timestamp
	 * @param token
	 * @param pages
	 * @param latitude
	 * @param longitude
	 * @return MomentDTO
	 * @throws ParameterException
	 * @throws BusinessException
	 */
	public List<MomentDTO> showMoment(Integer uid, Integer type, Long timestamp, String token, Integer pages,
			BigDecimal latitude, BigDecimal longitude) throws ParameterException, BusinessException;

	/**
	 * 添加评论内容
	 * 
	 * @param msg
	 * @param mid
	 * @param uid
	 * @param reply_uid
	 * @param token
	 * @param timestamp
	 * @return CommentDTO
	 * @throws ParameterException
	 * @throws BusinessException
	 */
	public CommentDTO addComment(String msg, Integer mid, Integer uid, Integer reply_uid, String token, Long timestamp)
			throws ParameterException, BusinessException;

	/**
	 * 删除评论内容
	 * 
	 * @param uid
	 * @param comment_id
	 * @param token
	 * @param timestamp
	 */
	public void removeComment(String token, Integer uid, Integer comment_id, Long timestamp);

	/**
	 * 删除评论内容
	 * 
	 * @param uid
	 * @param mid
	 * @param token
	 * @param timestamp
	 */
	ThumbsUpDTO addThumbsup(String token, Integer uid, Integer mid, Long timestamp);

	public void removeThumbsup(String token, Integer uid, Integer thumbs_up_id, Long timestamp);

	public List<ThumbsUpDTO> showThumbsup(String token, Integer uid, Integer mid, Long timestamp, Integer pages);
}
