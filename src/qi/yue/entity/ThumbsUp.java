package qi.yue.entity;

import java.util.Date;

public class ThumbsUp {
	 private Integer id;

	    private Integer userId;

	    private String nickname;

	    private Integer momentId;

	    private String faceUrl;

	    private String signature;

	    private Date createdAt;

	    private Date updatedAt;

	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public Integer getUserId() {
	        return userId;
	    }

	    public void setUserId(Integer userId) {
	        this.userId = userId;
	    }

	    public String getNickname() {
	        return nickname;
	    }

	    public void setNickname(String nickname) {
	        this.nickname = nickname == null ? null : nickname.trim();
	    }

	    public Integer getMomentId() {
	        return momentId;
	    }

	    public void setMomentId(Integer momentId) {
	        this.momentId = momentId;
	    }

	    public String getFaceUrl() {
	        return faceUrl;
	    }

	    public void setFaceUrl(String faceUrl) {
	        this.faceUrl = faceUrl == null ? null : faceUrl.trim();
	    }

	    public String getSignature() {
	        return signature;
	    }

	    public void setSignature(String signature) {
	        this.signature = signature == null ? null : signature.trim();
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