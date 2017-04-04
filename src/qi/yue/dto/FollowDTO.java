package qi.yue.dto;

import java.util.Date;

public class FollowDTO {
	private Integer id;

	private Integer fid;

	private String follower_face_url;

	private String follower_signature;

	private String follower_username;

	private Integer tid;

	private String followed_face_url;

	private String followed_signature;

	private String followed_username;

	private Date createdAt;

	private Date updatedAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getFollower_face_url() {
		return follower_face_url;
	}

	public void setFollower_face_url(String follower_face_url) {
		this.follower_face_url = follower_face_url;
	}

	public String getFollower_signature() {
		return follower_signature;
	}

	public void setFollower_signature(String follower_signature) {
		this.follower_signature = follower_signature;
	}

	public String getFollower_username() {
		return follower_username;
	}

	public void setFollower_username(String follower_username) {
		this.follower_username = follower_username;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public String getFollowed_face_url() {
		return followed_face_url;
	}

	public void setFollowed_face_url(String followed_face_url) {
		this.followed_face_url = followed_face_url;
	}

	public String getFollowed_signature() {
		return followed_signature;
	}

	public void setFollowed_signature(String followed_signature) {
		this.followed_signature = followed_signature;
	}

	public String getFollowed_username() {
		return followed_username;
	}

	public void setFollowed_username(String followed_username) {
		this.followed_username = followed_username;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}