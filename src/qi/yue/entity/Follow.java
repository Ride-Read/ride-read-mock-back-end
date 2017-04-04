package qi.yue.entity;

import java.util.Date;

public class Follow {
	private Integer id;

	private Integer fid;

	private String followerFaceUrl;

	private String followerSignature;

	private String followerUsername;

	private Integer tid;

	private String followedFaceUrl;

	private String followedSignature;

	private String followedUsername;

	private String followedRemark;

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

	public String getFollowerFaceUrl() {
		return followerFaceUrl;
	}

	public void setFollowerFaceUrl(String followerFaceUrl) {
		this.followerFaceUrl = followerFaceUrl == null ? null : followerFaceUrl.trim();
	}

	public String getFollowerSignature() {
		return followerSignature;
	}

	public void setFollowerSignature(String followerSignature) {
		this.followerSignature = followerSignature == null ? null : followerSignature.trim();
	}

	public String getFollowerUsername() {
		return followerUsername;
	}

	public void setFollowerUsername(String followerUsername) {
		this.followerUsername = followerUsername == null ? null : followerUsername.trim();
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public String getFollowedFaceUrl() {
		return followedFaceUrl;
	}

	public void setFollowedFaceUrl(String followedFaceUrl) {
		this.followedFaceUrl = followedFaceUrl == null ? null : followedFaceUrl.trim();
	}

	public String getFollowedSignature() {
		return followedSignature;
	}

	public void setFollowedSignature(String followedSignature) {
		this.followedSignature = followedSignature == null ? null : followedSignature.trim();
	}

	public String getFollowedUsername() {
		return followedUsername;
	}

	public void setFollowedUsername(String followedUsername) {
		this.followedUsername = followedUsername == null ? null : followedUsername.trim();
	}

	public String getFollowedRemark() {
		return followedRemark;
	}

	public void setFollowedRemark(String followedRemark) {
		this.followedRemark = followedRemark;
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