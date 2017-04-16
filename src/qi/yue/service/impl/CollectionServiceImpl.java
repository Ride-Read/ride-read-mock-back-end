package qi.yue.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import qi.yue.common.MessageCommon;
import qi.yue.dao.mapper.CollectionMapper;
import qi.yue.dto.CollectionDTO;
import qi.yue.dto.MomentDTO;
import qi.yue.dto.UserDTO;
import qi.yue.dto.assembler.CollectionDTOAssembler;
import qi.yue.entity.Collection;
import qi.yue.exception.BusinessException;
import qi.yue.exception.ParameterException;
import qi.yue.service.CollectionService;
import qi.yue.service.MomentService;
import qi.yue.service.UserService;
import qi.yue.utils.CommonUtil;

@Service
public class CollectionServiceImpl implements CollectionService {
	@Resource
	CollectionMapper collectionMapper;

	@Resource
	UserService userService;
	@Resource
	MomentService momentService;

	@Override
	public int save(Collection collection) {
		return collectionMapper.insert(collection);
	}

	@Override
	public CollectionDTO save(String token, Integer uid, Integer mid, Integer type, Long timestamp) {
		if (CommonUtil.isNullOrEmpty(token) || CommonUtil.isNull(uid) || CommonUtil.isNull(type)
				|| CommonUtil.isNull(mid) || CommonUtil.isNull(timestamp)) {
			throw new ParameterException();
		}
		// UserDTO user = userService.find(uid);
		Map<String, Object> map = new HashMap<>();
		map.put("uid", uid);
		map.put("mid", mid);
		CollectionDTO collectionDTO = collectionMapper.findIdAndMid(map);
		if (!CommonUtil.isNullOrEmpty(collectionDTO)) {
			throw new BusinessException(MessageCommon.STATUS_REPEAT_COLLECT, MessageCommon.FAIL_MESSAGE_REPEAT_COLLECT);
		}
		MomentDTO moment = momentService.find(mid);
		UserDTO userOfMoment = userService.find(moment.getUid());
		String pictureStr = moment.getPictureString();
		Collection collection = new Collection();
		collection.setCreateAt(new Date());
		if (!CommonUtil.isNullOrEmpty(pictureStr)) {
			String firstPicture = pictureStr.split(",")[0];
			collection.setFirstPicture(firstPicture);
		}
		collection.setMid(mid);
		String momentMsg = moment.getMsg();
		if (!CommonUtil.isNullOrEmpty(momentMsg)) {
			if (momentMsg.length() > 15) {
				String msgStr = momentMsg.substring(0, 14);
				msgStr = msgStr + "...";
			}
			collection.setMsg(momentMsg);
		}
		collection.setType(type);
		collection.setUid(uid);
		collection.setUpdateAt(new Date());
		collection.setUsername(userOfMoment.getUsername());
		collectionMapper.insert(collection);
		return CollectionDTOAssembler.toDto(collection);
	}

	public List<CollectionDTO> showCollections(String token, Integer uid, Long timestamp) {
		if (CommonUtil.isNullOrEmpty(token) || CommonUtil.isNull(uid) || CommonUtil.isNull(timestamp)) {
			throw new ParameterException();
		}
		return collectionMapper.findCollectionsByUid(uid);
	}
}
