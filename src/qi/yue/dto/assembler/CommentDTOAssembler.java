package qi.yue.dto.assembler;

import qi.yue.dto.CommentDTO;
import qi.yue.entity.Comment;
import qi.yue.utils.CommonUtil;

public class CommentDTOAssembler {
	/**
	 * @param comment
	 * @return
	 */
	public static CommentDTO toDto(Comment comment) {
		CommentDTO dto = new CommentDTO();
		if (!CommonUtil.isNull(comment)) {
			dto.setComment_id(comment.getId());
			dto.setCreated_at(comment.getCreatedAt());
			dto.setFace_url(comment.getFaceUrl());
			dto.setMid(comment.getMomentId());
			dto.setMsg(comment.getMsg());
			dto.setReply_uid(comment.getReplyUid());
			dto.setReply_username(comment.getReplyUsername());
			dto.setUsername(comment.getUsername());
			dto.setUid(comment.getUserId());
			dto.setUpdated_at(comment.getUpdatedAt());
		}
		return dto;
	}
}
