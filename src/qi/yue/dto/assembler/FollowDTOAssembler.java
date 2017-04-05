package qi.yue.dto.assembler;

import java.util.ArrayList;
import java.util.List;

import qi.yue.dto.FollowDTO;
import qi.yue.entity.Follow;
import qi.yue.utils.CommonUtil;

public class FollowDTOAssembler {
	public static FollowDTO toDto(Follow follow) {
		FollowDTO dto = new FollowDTO();
		if (!CommonUtil.isNull(follow)) {
			dto.setId(follow.getId());
			dto.setFid(follow.getFid());
			dto.setTid(follow.getTid());
			dto.setFollower_face_url(follow.getFollowerFaceUrl());
			dto.setFollower_signature(follow.getFollowerSignature());
			dto.setFollower_username(follow.getFollowerUsername());
			dto.setTid(follow.getTid());
			dto.setFollowed_face_url(follow.getFollowedFaceUrl());
			dto.setFollowed_signature(follow.getFollowedSignature());
			dto.setFollowed_username(follow.getFollowedUsername());
			dto.setCreated_at(follow.getCreatedAt());
			dto.setUpdated_at(follow.getUpdatedAt());
		}

		return dto;

	}

	public static List<FollowDTO> toDtoList(List<Follow> followList) {
		List<FollowDTO> list = new ArrayList<FollowDTO>();
		if (!CommonUtil.isNullOrEmpty(followList)) {
			for (Follow follow : followList) {
				list.add(toDto(follow));
			}
		}
		return list;
	}
}
