package qi.yue.dto.assembler;

import qi.yue.dto.UserDto;
import qi.yue.entity.User;
import qi.yue.utils.CommonUtil;

public class UserDtoAssembler {
	/**
	 * @param thumbsUp
	 * @return
	 */
	public static UserDto toDto(User user) {
		UserDto dto = new UserDto();
		if (!CommonUtil.isNull(user)) {
			dto.setBirthday(user.getBirthday());
			dto.setCareer(user.getCareer());
			dto.setCreated_at(user.getCreatedAt());
			dto.setFace_url(user.getFaceUrl());
			dto.setFollower(user.getFollower());
			dto.setFollowing(user.getFollowing());
			dto.setHometown(user.getHometown());
			dto.setLocation(user.getLocation());
			dto.setNickname(user.getNickname());
			dto.setPassword(user.getPassword());
			dto.setPhonenumber(user.getPhonenumber());
			dto.setSchool(user.getSchool());
			dto.setSex(user.getSex());
			dto.setSignature(user.getSignature());
			// dto.setTags(user.ge);
			dto.setToken(user.getToken());
			dto.setUid(user.getId());
			dto.setUpdated_at(user.getUpdatedAt());
			dto.setUsername(user.getUsername());
		}

		return dto;

	}
}
