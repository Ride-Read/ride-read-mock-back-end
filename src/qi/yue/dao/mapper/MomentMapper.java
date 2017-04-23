package qi.yue.dao.mapper;

import java.util.List;
import java.util.Map;

import qi.yue.dto.MomentDTO;
import qi.yue.dto.PageDTO;
import qi.yue.entity.Moment;

public interface MomentMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(Moment moment);

	MomentDTO find(Integer id);

	List<MomentDTO> findByUserId(Integer userId);

	int delete(Integer id);

	List<MomentDTO> findByPage(PageDTO pageDTO);

	List<MomentDTO> findFollowingsMoment(Map<String, Object> map);

	List<MomentDTO> findNearbyMoment(Map<String, Object> map);

	List<MomentDTO> findUserMoment(Map<String, Object> map);

	List<MomentDTO> findUserMap(Integer userId);

	List<MomentDTO> findNearMap(Map<String, Object> map);

	MomentDTO findMoment(Map<String, Object> map);

	List<MomentDTO> findWithtThumbsUpCount(Map<String, Object> map);
}