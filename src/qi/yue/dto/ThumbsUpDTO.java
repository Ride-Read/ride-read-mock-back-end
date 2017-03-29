package qi.yue.dto;

import java.util.Date;

public class ThumbsUpDTO {
	private Integer thumbs_up_id;

	private String nickname;

	private Integer uid;

	private Integer mid;

	private String face_Url;

	private String signature;

	private Date created_at;

	private Date updated_at;

	public Integer getThumbs_up_id() {
		return thumbs_up_id;
	}

	public void setThumbs_up_id(Integer thumbs_up_id) {
		this.thumbs_up_id = thumbs_up_id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	public String getFace_Url() {
		return face_Url;
	}

	public void setFace_Url(String face_Url) {
		this.face_Url = face_Url;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

}