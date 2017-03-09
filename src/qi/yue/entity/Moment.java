package qi.yue.entity;

public class Moment {
	private Integer userid;

	private Integer type;

	private String cover;

	private String msg;

	private String video;

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover == null ? null : cover.trim();
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg == null ? null : msg.trim();
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video == null ? null : video.trim();
	}
}