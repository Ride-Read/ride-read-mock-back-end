package qi.yue.dto.assembler;

import java.util.ArrayList;
import java.util.List;

import qi.yue.dto.CollectionDTO;
import qi.yue.dto.FollowDTO;
import qi.yue.entity.Collection;
import qi.yue.entity.Follow;
import qi.yue.utils.CommonUtil;

public class CollectionDTOAssembler {
	public static CollectionDTO toDto(Collection entity) {
		CollectionDTO dto = new CollectionDTO();
		if (!CommonUtil.isNull(entity)) {
			dto.setId(entity.getId());
			dto.setMid(entity.getMid());
			dto.setUid(entity.getUid());
			dto.setFirst_picture(entity.getFirstPicture());
			dto.setUsername(entity.getUsername());
			dto.setType(entity.getType());
			dto.setCreate_at(entity.getCreateAt());
			dto.setUpdate_at(entity.getUpdateAt());
			dto.setFace_url(entity.getFaceUrl());
		}
		return dto;
	}

	public static List<CollectionDTO> toDtoList(List<Collection> entityList) {
		List<CollectionDTO> list = new ArrayList<CollectionDTO>();
		if (!CommonUtil.isNullOrEmpty(entityList)) {
			for (Collection entity : entityList) {
				list.add(toDto(entity));
			}
		}
		return list;
	}
}
