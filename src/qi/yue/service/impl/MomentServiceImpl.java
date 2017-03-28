package qi.yue.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import qi.yue.entity.Moment;
import qi.yue.dao.mapper.FollowingMapper;
import qi.yue.dao.mapper.MomentMapper;
import qi.yue.dto.FollowingDTO;
import qi.yue.dto.MomentDTO;
import qi.yue.dto.PageDTO;
import qi.yue.service.MomentService;
import qi.yue.utils.CommonUtil;

@Service
public class MomentServiceImpl implements MomentService {
	@Resource
	private MomentMapper momentMapper;

	@Resource
	private FollowingMapper followingMapper;

	@Override
	public int save(Moment moment) {
		return momentMapper.insert(moment);
	}

	@Override
	public MomentDTO find(int id) {
		return momentMapper.find(id);
	}

	@Override
	public List<MomentDTO> findByUserId(int userId) {
		return momentMapper.findByUserId(userId);
	}

	@Override
	public int delete(int id) {
		return momentMapper.delete(id);
	}

	@Override
	public List<MomentDTO> findByPage(PageDTO pageDTO) {
		return momentMapper.findByPage(pageDTO);
	}

	@Override
	public List<MomentDTO> findFollowingsMoment(Map<String, Object> map) {
		List<MomentDTO> MomentDTOList = momentMapper.findFollowingsMoment(map);
		for (MomentDTO momentDTO : MomentDTOList) {
			Map<String, Object> mapTemp1 = new HashMap<String, Object>();
			mapTemp1.put("fid", map.get("fid"));
			mapTemp1.put("tid", momentDTO.getUid());
			FollowingDTO followingDTO = followingMapper.findByFidAndTid(map);
			if (CommonUtil.isNull(followingDTO)) {
				momentDTO.getUser().setIs_followed(1);
			} else {
				momentDTO.getUser().setIs_followed(0);
			}
			Double distance = momentDTO.getDistance().divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP)
					.doubleValue();
			momentDTO.setDistanceString(distance.toString() + "km");
		}
		return MomentDTOList;
	}

	@Override
	public List<MomentDTO> findNearbyMoment(Map<String, Object> map) {
		List<MomentDTO> MomentDTOList = momentMapper.findNearbyMoment(map);
		for (MomentDTO momentDTO : MomentDTOList) {
			Map<String, Object> mapTemp1 = new HashMap<String, Object>();
			mapTemp1.put("fid", map.get("fid"));
			mapTemp1.put("tid", momentDTO.getUid());
			FollowingDTO followingDTO1 = followingMapper.findByFidAndTid(mapTemp1);

			Map<String, Object> mapTemp2 = new HashMap<String, Object>();
			mapTemp2.put("fid", momentDTO.getUid());
			mapTemp2.put("tid", mapTemp2.get("fid"));
			FollowingDTO followingDTO2 = followingMapper.findByFidAndTid(mapTemp2);
			if (CommonUtil.isNull(followingDTO1) && CommonUtil.isNull(followingDTO2)) {
				momentDTO.getUser().setIs_followed(-1);
			} else if (!CommonUtil.isNull(followingDTO1) && !CommonUtil.isNull(followingDTO2)) {
				momentDTO.getUser().setIs_followed(1);
			} else {
				momentDTO.getUser().setIs_followed(0);
			}
			Double distance = momentDTO.getDistance().divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP)
					.doubleValue();
			momentDTO.setDistanceString(distance.toString() + "km");
		}
		return MomentDTOList;
	}
}
