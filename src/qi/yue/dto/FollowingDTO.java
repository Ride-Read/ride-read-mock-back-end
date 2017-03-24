package qi.yue.dto;

import java.util.Date;

public class FollowingDTO {
	private Integer following_id;

	private Integer fid;// 粉丝id

	private Integer tid;// 被关注用户id

	private String face_url;

	private String signature;

	private String nickname;

	private Date created_at;

	private Date updated_at;

	public Integer getFollowing_id() {
		return following_id;
	}

	public void setFollowing_id(Integer following_id) {
		this.following_id = following_id;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public String getFace_url() {
		return face_url;
	}

	public void setFace_url(String face_url) {
		this.face_url = face_url;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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