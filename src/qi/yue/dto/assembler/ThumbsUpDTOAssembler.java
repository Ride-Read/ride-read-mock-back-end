package qi.yue.dto.assembler;

import qi.yue.dto.ThumbsUpDTO;
import qi.yue.entity.ThumbsUp;
import qi.yue.utils.CommonUtil;

public class ThumbsUpDTOAssembler {
	/**
	 * @param thumbsUp
	 * @return
	 */
	public static ThumbsUpDTO toDto(ThumbsUp thumbsUp) {
		ThumbsUpDTO dto = new ThumbsUpDTO();
		if (!CommonUtil.isNull(thumbsUp)) {
			dto.setCreated_at(thumbsUp.getCreatedAt());
			dto.setMid(thumbsUp.getMomentId());
			dto.setUsername(thumbsUp.getUsername());
			dto.setFace_Url(thumbsUp.getFaceUrl());
			dto.setSignature(thumbsUp.getSignature());
			dto.setThumbs_up_id(thumbsUp.getId());
			dto.setUid(thumbsUp.getUserId());
			dto.setUpdated_at(thumbsUp.getUpdatedAt());
		}

		return dto;

	}
}
