package qi.yue.dao.mapper;

import java.util.List;

import qi.yue.dto.MomentDto;
import qi.yue.entity.Moment;

public interface MomentMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(Moment moment);

	MomentDto find(int id);

	List<MomentDto> findByUserId(int userId);

}