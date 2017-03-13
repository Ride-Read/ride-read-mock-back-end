package qi.yue.dao.mapper;

import java.util.List;

import qi.yue.entity.Moment;

public interface MomentMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(Moment moment);

	Moment find(int id);

	List<Moment> findByUserId(int userId);

}