package qi.yue.service;

import java.util.List;
import java.util.Map;

import qi.yue.dto.MomentDTO;
import qi.yue.dto.PageDTO;
import qi.yue.entity.Moment;

public interface MomentService {
	int save(Moment moment);

	MomentDTO find(int id);

	List<MomentDTO> findByUserId(int userId);

	List<MomentDTO> findByPage(PageDTO pageDTO);

	int delete(int id);

	List<MomentDTO> findFollowingsMoment(Map<String, Object> map);

	List<MomentDTO> findNearbyMoment(Map<String, Object> map);
}
