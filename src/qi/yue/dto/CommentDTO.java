package qi.yue.dto;

import java.util.Date;

public class CommentDTO {
	private Integer comment_id;
	private String username;
	private Integer uid;
	private Integer mid;
	private String face_url;
	private Integer reply_uid;
	private String reply_username;
	private Date created_at;
	private String msg;
	private Date updated_at;

	public Integer getComment_id() {
		return comment_id;
	}

	public void setComment_id(Integer comment_id) {
		this.comment_id = comment_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getFace_url() {
		return face_url;
	}

	public void setFace_url(String face_url) {
		this.face_url = face_url;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public Integer getReply_uid() {
		return reply_uid;
	}

	public void setReply_uid(Integer reply_uid) {
		this.reply_uid = reply_uid;
	}

	public String getReply_username() {
		return reply_username;
	}

	public void setReply_username(String reply_username) {
		this.reply_username = reply_username;
	}
}