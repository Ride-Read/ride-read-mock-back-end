package qi.yue.service;

import java.util.List;
import java.util.Map;

import qi.yue.dto.MomentDto;
import qi.yue.entity.Moment;

public interface MomentService {
	int save(Moment moment);

	MomentDto find(int id);

	List<MomentDto> findByUserId(int userId);

	Map<String, Object> showMoment(int userId);
}
