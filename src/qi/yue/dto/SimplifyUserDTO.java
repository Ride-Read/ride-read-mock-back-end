package qi.yue.dto;

public class SimplifyUserDTO {
	private Integer uid;

	private String username;

	private Integer sex;

	private String face_url;

	private Integer is_followed;// 0-互相关注，1-单项关注，-1-无效

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getFace_url() {
		return face_url;
	}

	public void setFace_url(String face_url) {
		this.face_url = face_url;
	}

	public Integer getIs_followed() {
		return is_followed;
	}

	public void setIs_followed(Integer is_followed) {
		this.is_followed = is_followed;
	}

}
