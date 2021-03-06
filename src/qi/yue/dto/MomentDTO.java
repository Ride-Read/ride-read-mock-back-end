package qi.yue.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class MomentDTO {

	private Integer mid;

	private Integer uid;

	private Integer type;

	private String cover;

	private String video;

	private String msg;

	private String thumbs;

	private String pictureString;

	private Date created_at;

	private Date updated_at;

	private String[] pictures;

	private SimplifyUserDTO user;

	private List<CommentDTO> comment;

	private List<ThumbsUpDTO> thumbs_up;

	private BigDecimal latitude;

	private BigDecimal longitude;

	private BigDecimal distance;

	private String distance_string;

	private String moment_location;

	private String first_picture;

	private Integer count;

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
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
		this.cover = cover;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getThumbs() {
		return thumbs;
	}

	public void setThumbs(String thumbs) {
		this.thumbs = thumbs;
	}

	public String getPictureString() {
		return pictureString;
	}

	public void setPictureString(String pictureString) {
		this.pictureString = pictureString;
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

	public String[] getPictures() {
		return pictures;
	}

	public void setPictures(String[] pictures) {
		this.pictures = pictures;
	}

	public List<CommentDTO> getComment() {
		return comment;
	}

	public void setComment(List<CommentDTO> comment) {
		this.comment = comment;
	}

	public List<ThumbsUpDTO> getThumbs_up() {
		return thumbs_up;
	}

	public void setThumbs_up(List<ThumbsUpDTO> thumbs_up) {
		this.thumbs_up = thumbs_up;
	}

	public SimplifyUserDTO getUser() {
		return user;
	}

	public void setUser(SimplifyUserDTO user) {
		this.user = user;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getDistance() {
		return distance;
	}

	public void setDistance(BigDecimal distance) {
		this.distance = distance;
	}

	public String getDistance_string() {
		return distance_string;
	}

	public void setDistance_string(String distance_string) {
		this.distance_string = distance_string;
	}

	public String getMoment_location() {
		return moment_location;
	}

	public void setMoment_location(String moment_location) {
		this.moment_location = moment_location;
	}

	public String getFirst_picture() {
		return first_picture;
	}

	public void setFirst_picture(String first_picture) {
		this.first_picture = first_picture;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}