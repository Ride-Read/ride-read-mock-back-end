package qi.yue.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import qi.yue.common.MessageCommon;
import qi.yue.dao.mapper.FollowerMapper;
import qi.yue.dto.FollowerDTO;
import qi.yue.entity.Follower;
import qi.yue.exception.BusinessException;
import qi.yue.exception.ParameterException;
import qi.yue.service.FollowerService;
import qi.yue.utils.CommonUtil;

@Service
public class FollowerServiceImpl implements FollowerService {
	@Resource
	private FollowerMapper followerMapper;

	/**
	 * 查询关注该用户的所有用户列表
	 * @param tid
	 * @return List<FollowerDTO>
	 */
	private List<FollowerDTO> findByTid(int tid) throws BusinessException{
		try {
			return followerMapper.findByTid(tid);
		}catch(BusinessException e) {
			throw new BusinessException(MessageCommon.STATUS_QUERY_FAIL,
					MessageCommon.FAIL_MESSAGE_QUERY_FAIL);
		}

	}

	@Override
	public int save(Follower follower) {
		return followerMapper.insert(follower);
	}

	@Override
	public List<FollowerDTO> findByFid(int fid) {
		return followerMapper.findByFid(fid);
	}

	@Override
	public int deleteByFid(int fid) {
		return followerMapper.deleteByFid(fid);
	}

	@Override
	public List<FollowerDTO> queryFollower(Integer uid, String token, Long timestamp)
			throws ParameterException, BusinessException {
		if (CommonUtil.isNull(uid) || CommonUtil.isNullOrEmpty(token) || 
				CommonUtil.isNullOrEmpty(timestamp)) {
			throw new ParameterException();
		}
		
		List<FollowerDTO> dtoList = findByTid(uid);
		return dtoList;
	}
}
