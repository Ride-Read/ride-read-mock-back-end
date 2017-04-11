package qi.yue.dto.assembler;

import qi.yue.dto.MomentDTO;
import qi.yue.entity.Moment;
import qi.yue.utils.CommonUtil;

public class MomentDTOAssembler {
	/**
	 * @param moment
	 * @return MomentDto
	 */
	public static MomentDTO toDto(Moment moment) {
		MomentDTO dto = new MomentDTO();
		if (!CommonUtil.isNull(moment)) {
			dto.setComment(null);
			dto.setCover(moment.getCover());
			dto.setCreated_at(moment.getCreatedAt());
			dto.setMid(moment.getId());
			dto.setMsg(moment.getMsg());
			dto.setPictureString(moment.getPictures());
			if (!CommonUtil.isNullOrEmpty(moment.getPictures())) {
				dto.setPictures(moment.getPictures().split(","));
			}
			dto.setLatitude(moment.getLatitude());
			dto.setLongitude(moment.getLongitude());
			dto.setThumbs(moment.getThumbs());
			dto.setThumbs_up(null);
			dto.setType(moment.getType());
			dto.setUid(moment.getUserId());
			dto.setUpdated_at(moment.getUpdatedAt());
			dto.setVideo(moment.getVideo());
			dto.setMoment_location(moment.getMoment_location());
		}

		return dto;

	}
}
