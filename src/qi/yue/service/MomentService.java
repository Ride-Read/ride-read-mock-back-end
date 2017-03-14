package qi.yue.service;

import java.util.List;
import qi.yue.dto.MomentDto;
import qi.yue.entity.Moment;

public interface MomentService {
	int save(Moment moment);

	MomentDto find(int id);

	List<MomentDto> findByUserId(int userId);

	int delete(int id);
}
