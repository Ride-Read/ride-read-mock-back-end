package qi.yue.service;

import java.util.List;
import qi.yue.dto.MomentDTO;
import qi.yue.dto.PageDTO;
import qi.yue.entity.Moment;

public interface MomentService {
	int save(Moment moment);

	MomentDTO find(int id);

	List<MomentDTO> findByUserId(int userId);

	List<MomentDTO> findByPage(PageDTO pageDTO);

	int delete(int id);

	List<MomentDTO> findFollowingsMoment(PageDTO pageDTO);

	List<MomentDTO> findNearbyMoment(PageDTO pageDTO);
}
