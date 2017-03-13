package qi.yue.service;

import java.util.List;
import java.util.Map;

import qi.yue.entity.Moment;

public interface MomentService {
	int save(Moment moment);

	Moment find(int id);

	List<Moment> findByUserId(int userId);

	Map<String, Object> showMoment(int userId);
}
