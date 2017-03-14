package qi.yue.dto.assembler;

import qi.yue.dto.MomentDto;
import qi.yue.entity.Moment;
import qi.yue.utils.CommonUtil;

public class MomentDtoAssembler {
	/**
	 * @param moment
	 * @return MomentDto
	 */
	public static MomentDto toDto(Moment moment) {
		MomentDto dto = new MomentDto();
		if (!CommonUtil.isNull(moment)) {
			dto.setComment(null);
			dto.setCover(moment.getCover());
			dto.setCreated_at(moment.getCreatedAt());
			dto.setMid(moment.getId());
			dto.setMsg(moment.getMsg());
			dto.setPictures(moment.getPictures());
			dto.setThumbs(moment.getThumbs());
			dto.setThumbs_up(null);
			dto.setType(moment.getType());
			dto.setUid(moment.getUserId());
			dto.setUpdated_at(moment.getUpdatedAt());
			dto.setVideo(moment.getVideo());
		}

		return dto;

	}
}
