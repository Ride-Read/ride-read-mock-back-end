package qi.yue.dao.mapper;

import java.util.List;

import qi.yue.dto.MomentDTO;
import qi.yue.entity.Moment;

public interface MomentMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(Moment moment);

	MomentDTO find(int id);

	List<MomentDTO> findByUserId(int userId);

	int delete(int id);

}