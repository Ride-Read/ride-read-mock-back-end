package qi.yue.service;

import java.util.List;
import qi.yue.dto.MomentDTO;
import qi.yue.entity.Moment;

public interface MomentService {
	int save(Moment moment);

	MomentDTO find(int id);

	List<MomentDTO> findByUserId(int userId);

	int delete(int id);
}
