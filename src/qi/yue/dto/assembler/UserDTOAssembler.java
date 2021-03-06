package qi.yue.dto.assembler;

import qi.yue.dto.UserDTO;
import qi.yue.entity.User;
import qi.yue.utils.CommonUtil;

public class UserDTOAssembler {
	/**
	 * @param thumbsUp
	 * @return
	 */
	public static UserDTO toDto(User user) {
		UserDTO dto = new UserDTO();
		if (!CommonUtil.isNull(user)) {
			dto.setBirthday(user.getBirthday());
			dto.setCareer(user.getCareer());
			dto.setCreated_at(user.getCreatedAt());
			dto.setFace_url(user.getFaceUrl());
			dto.setFollower(user.getFollower());
			dto.setFollowing(user.getFollowing());
			dto.setHometown(user.getHometown());
			dto.setLocation(user.getLocation());
			dto.setPassword(user.getPassword());
			dto.setPhonenumber(user.getPhonenumber());
			dto.setSchool(user.getSchool());
			dto.setSex(user.getSex());
			dto.setSignature(user.getSignature());
			dto.setTagString(user.getTags());
			if (!CommonUtil.isNullOrEmpty(dto.getTagString())) {
				dto.setTags(dto.getTagString().split(","));
			}
			dto.setLatitude(user.getLatitude());
			dto.setLongitude(user.getLongitude());
			dto.setToken(user.getToken());
			dto.setUid(user.getId());
			dto.setUpdated_at(user.getUpdatedAt());
			dto.setUsername(user.getUsername());
			dto.setRide_read_id(user.getRideReadId());
		}

		return dto;

	}
}
