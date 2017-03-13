package qi.yue.dto.assembler;

import qi.yue.dto.CommentDto;
import qi.yue.entity.Comment;
import qi.yue.utils.CommonUtil;

public class CommentDtoAssembler {
	/**
	 * @param comment
	 * @return
	 */
	public static CommentDto toDto(Comment comment) {
		CommentDto dto = new CommentDto();
		if (!CommonUtil.isNull(comment)) {
			dto.setComment_id(comment.getId());
			dto.setCreated_at(comment.getCreatedAt());
			dto.setFace_url(comment.getFaceUrl());
			dto.setMid(comment.getMomentId());
			dto.setMsg(comment.getMsg());
			dto.setNickname(comment.getNickname());
			dto.setUid(comment.getUserId());
			dto.setUpdated_at(comment.getUpdatedAt());
		}

		return dto;

	}
}
