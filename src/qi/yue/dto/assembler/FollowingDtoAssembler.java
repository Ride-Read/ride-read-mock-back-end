package qi.yue.dto.assembler;

import java.util.ArrayList;
import java.util.List;

import qi.yue.dto.FollowingDTO;
import qi.yue.entity.Following;
import qi.yue.utils.CommonUtil;

public class FollowingDtoAssembler {
	/**
	 * @param comment
	 * @return
	 */
	public static FollowingDTO toDto(Following following) {
		FollowingDTO dto = new FollowingDTO();
		if (!CommonUtil.isNull(following)) {
			dto.setCreated_at(following.getCreatedAt());
			dto.setFace_url(following.getFaceUrl());
			dto.setFid(following.getFid());
			dto.setFollowing_id(following.getFid());
			dto.setNickname(following.getNickname());
			dto.setSignature(following.getSignature());
			dto.setTid(following.getTid());
			dto.setUpdated_at(following.getUpdatedAt());
		}

		return dto;

	}

	public static List<FollowingDTO> toDtoList(List<Following> followingList) {
		List<FollowingDTO> list = new ArrayList<FollowingDTO>();
		if (!CommonUtil.isNullOrEmpty(followingList)) {
			for (Following following : followingList) {
				list.add(toDto(following));
			}
		}
		return list;
	}
}
