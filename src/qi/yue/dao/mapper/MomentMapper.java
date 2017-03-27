package qi.yue.dao.mapper;

import java.util.List;
import java.util.Map;

import qi.yue.dto.MomentDTO;
import qi.yue.dto.PageDTO;
import qi.yue.entity.Moment;

public interface MomentMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(Moment moment);

	MomentDTO find(int id);

	List<MomentDTO> findByUserId(int userId);

	int delete(int id);

	List<MomentDTO> findByPage(PageDTO pageDTO);

	List<MomentDTO> findFollowingsMoment(Map<String, Object> map);

	List<MomentDTO> findNearbyMoment(Map<String, Object> map);

}